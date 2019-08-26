package com.whzw.yz.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.whzw.yz.exception.GlobalException;
import com.whzw.yz.mapper.OrderLogMapper;
import com.whzw.yz.mapper.SeatOrderMapper;
import com.whzw.yz.pojo.OrderLog;
import com.whzw.yz.pojo.SeatOrder;
import com.whzw.yz.pojo.TimeQuantum;
import com.whzw.yz.result.CodeMsg;
import com.whzw.yz.util.LoginUtil;
import com.whzw.yz.vo.OrderVo;

/**
 * 座位预约
 * 
 * @author wubn
 *
 */
@Service
@Transactional
public class SeatOrderService {

	@Autowired
	SeatOrderMapper seatOrderMapper;
	@Autowired
	OrderLogMapper orderLogMapper;

	private static Map<String, Date> orderMap = new ConcurrentHashMap<>();

	/**
	 * 预定座位，判断session中的令牌与cookie中的令牌是否一致
	 * 
	 * @param orderVo
	 * @param request
	 * @return
	 */
	public boolean order(OrderVo orderVo, HttpServletRequest request) {

//		String studentId = LoginUtil.LoginCheck(request);

		String studentId = "20164545";// 测试用
		String orderCode = String.valueOf(orderVo.getYear()) + String.valueOf(orderVo.getMonth())
				+ String.valueOf(orderVo.getDay()) + String.valueOf(orderVo.getTimeQuantem()) + orderVo.getSeatId();
		System.out.println(orderCode);
		if (seatOrderMapper.getOrderCode(orderCode) != null) {
			throw new GlobalException(CodeMsg.SEAT_ALREDY_ORDERED);
		}
		createOrder(orderVo, studentId, orderCode);
		return true;
	}

	/**
	 * 创建预定记录
	 * 
	 * @param orderVo
	 * @param studentId
	 * @param orderCode2
	 */
	@SuppressWarnings("deprecation")
	private Boolean createOrder(OrderVo orderVo, String studentId, String orderCode) {
		try {
			Date currentDate = new Date();
			SeatOrder seatOrder = new SeatOrder();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			seatOrder.setOrderTime(currentDate);
			Date date = new Date(orderVo.getYear() - 1900, orderVo.getMonth() - 1, orderVo.getDay());
			if (date.before(currentDate)) {
				throw new GlobalException(CodeMsg.ORDER_TIME_PASS);
			}
			seatOrder.setDate(date);
			seatOrder.setStudentId(studentId);
			seatOrder.setSeatId(orderVo.getSeatId());
			seatOrder.setTimeQuantun(orderVo.getTimeQuantem());
			seatOrder.setOrderCode(orderCode);
			// 写入预定记录
			seatOrderMapper.addOrder(seatOrder);
			// 记录超时时间
			Date timeoutDate = getTimeoutDate(date, orderVo.getTimeQuantem());
			orderMap.put(seatOrder.getId(), timeoutDate);
			// 写入日志
			OrderLog orderLog = new OrderLog();
			orderLog.setSeatId(orderVo.getSeatId());
			orderLog.setStudentId(studentId);
			orderLog.setOrderId(seatOrder.getId());
			orderLog.setOrderTime(currentDate);
			if (orderVo.getTimeQuantem() == TimeQuantum.M.getInfo()) {
				orderLog.setEndTime(
						new Date(orderVo.getYear() - 1900, orderVo.getMonth() - 1, orderVo.getDay(), 12, 00, 00));
			} else if (orderVo.getTimeQuantem() == TimeQuantum.A.getInfo()) {
				orderLog.setEndTime(
						new Date(orderVo.getYear() - 1900, orderVo.getMonth() - 1, orderVo.getDay(), 18, 00, 00));
			} else if (orderVo.getTimeQuantem() == TimeQuantum.N.getInfo()) {
				orderLog.setEndTime(
						new Date(orderVo.getYear() - 1900, orderVo.getMonth() - 1, orderVo.getDay(), 22, 00, 00));
			}
			orderLogMapper.addLog(orderLog);

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * 计算超时时间
	 * 
	 * @param date
	 * @param timeQuantem
	 * @return
	 */
	private Date getTimeoutDate(Date date, char timeQuantem) {
		Date timeoutDate = null;
		Date currentDate = new Date();
		char currentQut = TimeQuantum.M.getInfo();
		currentQut = getCurrentQut(currentDate);
		// 临时预约
		if (date.getYear() == currentDate.getYear() && date.getMonth() == currentDate.getMonth()
				&& date.getDay() == currentDate.getDay() && currentQut == timeQuantem) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(calendar.MINUTE, 30);
			timeoutDate = calendar.getTime();
		}
		// 非临时预约
		else {
			timeoutDate = date;
			if (timeQuantem == TimeQuantum.M.getInfo()) {
				timeoutDate.setHours(12);
				timeoutDate.setMinutes(0);
				timeoutDate.setSeconds(0);
			} else if (timeQuantem == TimeQuantum.A.getInfo()) {
				timeoutDate.setHours(18);
				timeoutDate.setMinutes(0);
				timeoutDate.setSeconds(0);
			} else {
				timeoutDate.setHours(22);
				timeoutDate.setMinutes(0);
				timeoutDate.setSeconds(0);
			}
		}
		return timeoutDate;
	}

	/**
	 * 获取当前时间段
	 * 
	 * @param currentDate
	 * @param currentQut
	 * @return
	 */
	private char getCurrentQut(Date currentDate) {
		char currentQut = 0;
		// 上午时间段终点
		Date mDate = new Date();
		mDate.setHours(12);
		mDate.setMinutes(0);
		mDate.setSeconds(0);
		// 下午时间段终点
		Date aDate = new Date();
		aDate.setHours(16);
		aDate.setMinutes(0);
		aDate.setSeconds(0);

		// 计算当前时间段
		if (currentDate.before(mDate)) {
			currentQut = TimeQuantum.M.getInfo();
		} else if (currentDate.after(mDate) && currentDate.before(aDate)) {
			currentQut = TimeQuantum.A.getInfo();
		} else {
			currentQut = TimeQuantum.N.getInfo();
		}
		return currentQut;
	}

	/**
	 * 获取cookie
	 * 
	 * @param cookies
	 * @return
	 */
	private String getCookieValue(Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if (cookie.getName() == "token") {
				return cookie.getValue();
			}
		}
		return null;
	}

	/**
	 * 取消预定
	 * 
	 * @param orderId
	 */
	public void cancelOrder(String orderId) {
		seatOrderMapper.deleteOrder(orderId);
	}

	public Map<String, Date> getOrderMap() {
		return orderMap;
	}

}
