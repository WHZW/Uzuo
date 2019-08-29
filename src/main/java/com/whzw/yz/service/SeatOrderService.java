package com.whzw.yz.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.whzw.yz.exception.GlobalException;
import com.whzw.yz.mapper.OrderLogMapper;
import com.whzw.yz.mapper.SeatOrderMapper;
import com.whzw.yz.mapper.StudentMapper;
import com.whzw.yz.pojo.OrderLog;
import com.whzw.yz.pojo.SeatOrder;
import com.whzw.yz.pojo.TimeQuantum;
import com.whzw.yz.result.CodeMsg;
import com.whzw.yz.util.LoginUtil;
import com.whzw.yz.util.OrderCodeUtil;
import com.whzw.yz.vo.OrderVo;
import com.whzw.yz.vo.SeatOrderVo;

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

	@Autowired
	StudentMapper studentMapper;

	private static final Map<String, Date> orderMap = new ConcurrentHashMap<>();

	/**
	 * 预定座位，判断session中的令牌与cookie中的令牌是否一致
	 * 
	 * @param orderVo
	 * @param request
	 * @return
	 */
	public SeatOrderVo order(OrderVo orderVo, HttpServletRequest request) {
		String studentId = LoginUtil.LoginCheck(request);
//		String studentId = "20164545";// 测试用
		// 检查信用积分
		int integral = studentMapper.getIntegralByStudentId(studentId);
		if (integral < 60) {
			throw new GlobalException(CodeMsg.INTEGRAL_NOT_ENOUGH);
		}
		
		String orderCode = OrderCodeUtil.encode(orderVo);
		System.out.println(orderCode);
		if (seatOrderMapper.getOrderCode(orderCode) != null) {
			throw new GlobalException(CodeMsg.SEAT_ALREDY_ORDERED);
		}
		SeatOrderVo seatOrderVo = createOrder(orderVo, studentId, orderCode);
		return seatOrderVo;
	}

	/**
	 * 创建预定记录
	 * 
	 * @param orderVo
	 * @param studentId
	 * @param orderCode2
	 */
	@SuppressWarnings("deprecation")
	private SeatOrderVo createOrder(OrderVo orderVo, String studentId, String orderCode) {
		SeatOrder seatOrder = new SeatOrder();
		try {
			Date currentDate = Calendar.getInstance().getTime();
			seatOrder.setOrderTime(currentDate);
			Date date = new Date(orderVo.getYear() - 1900, orderVo.getMonth() - 1, orderVo.getDay());
			if(orderVo.getTimeQuantum() == 'M') {
				date.setHours(12);
				date.setMinutes(0);
				date.setSeconds(0);
			}else if(orderVo.getTimeQuantum() == 'A') {
				date.setHours(18);
				date.setMinutes(0);
				date.setSeconds(0);
			}else {
				date.setHours(22);
				date.setMinutes(0);
				date.setSeconds(0);
			}
			System.out.println(date);
			if (date.before(currentDate)) {
				throw new GlobalException(CodeMsg.ORDER_TIME_PASS);
			}
			seatOrder.setDate(date);
			seatOrder.setStudentId(studentId);
			seatOrder.setSeatId(orderVo.getSeatId());
			seatOrder.setTimeQuantun(orderVo.getTimeQuantum());
			seatOrder.setOrderCode(orderCode);
			// 写入预定记录
			seatOrderMapper.addOrder(seatOrder);
			// 写入日志
			OrderLog orderLog = new OrderLog();
			orderLog.setSeatId(orderVo.getSeatId());
			orderLog.setStudentId(studentId);
			orderLog.setOrderId(seatOrder.getId());
			orderLog.setOrderTime(currentDate);
			orderLog.setDate(seatOrder.getDate());
			orderLog.setTimeQuantum(seatOrder.getTimeQuantun());
			if (orderVo.getTimeQuantum() == TimeQuantum.M.getInfo()) {
				orderLog.setEndTime(
						new Date(orderVo.getYear() - 1900, orderVo.getMonth(), orderVo.getDay(), 12, 00, 00));
			} else if (orderVo.getTimeQuantum() == TimeQuantum.A.getInfo()) {
				orderLog.setEndTime(
						new Date(orderVo.getYear() - 1900, orderVo.getMonth(), orderVo.getDay(), 18, 00, 00));
			} else if (orderVo.getTimeQuantum() == TimeQuantum.N.getInfo()) {
				orderLog.setEndTime(
						new Date(orderVo.getYear() - 1900, orderVo.getMonth(), orderVo.getDay(), 22, 00, 00));
			}
			orderLogMapper.addLog(orderLog);
			// 记录超时时间
			Date timeoutDate = getTimeoutDate(date, orderVo.getTimeQuantum());
			orderMap.put(seatOrder.getId(), timeoutDate);
			return new SeatOrderVo(seatOrder);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 计算超时时间
	 * 
	 * @param date
	 * @param timeQuantem
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Date getTimeoutDate(Date date, char timeQuantem) {
		Date timeoutDate = null;
		Date currentDate = Calendar.getInstance().getTime();
		char currentQut = TimeQuantum.M.getInfo();
		currentQut = getCurrentQut(currentDate);
		// 临时预约
		if (date.getYear() == currentDate.getYear() && date.getMonth() == currentDate.getMonth()
				&& date.getDay() == currentDate.getDay() && currentQut == timeQuantem) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.MINUTE, 30);
			timeoutDate = calendar.getTime();
		}
		// 非临时预约
		else {
			timeoutDate = date;
			if (timeQuantem == TimeQuantum.M.getInfo()) {
				timeoutDate.setHours(9);
				timeoutDate.setMinutes(0);
				timeoutDate.setSeconds(0);
			} else if (timeQuantem == TimeQuantum.A.getInfo()) {
				timeoutDate.setHours(15);
				timeoutDate.setMinutes(0);
				timeoutDate.setSeconds(0);
			} else {
				timeoutDate.setHours(20);
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
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private char getCurrentQut(Date currentDate) {
		char currentQut = 0;
		// 上午时间段终点
		Date mDate = Calendar.getInstance().getTime();
		mDate.setHours(12);
		mDate.setMinutes(0);
		mDate.setSeconds(0);
		// 下午时间段终点
		Date aDate = Calendar.getInstance().getTime();
		aDate.setHours(18);
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
	 * 取消预定
	 * 
	 * @param orderId
	 * @return
	 */
	public boolean cancelOrder(String orderId) {
		try {
			// 查看数据库中是否有这条预约
			String dbOrderId = seatOrderMapper.getOrderIdByOrderId(orderId);
			if (dbOrderId == null) {
				throw new GlobalException(CodeMsg.ORDER_NOT_EXIST);
			}
			seatOrderMapper.deleteOrder(dbOrderId);
			orderLogMapper.updateStatus(dbOrderId, "取消");
			orderMap.remove(dbOrderId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw e;
		}
	}

	public Map<String, Date> getOrderMap() {
		return orderMap;
	}

}
