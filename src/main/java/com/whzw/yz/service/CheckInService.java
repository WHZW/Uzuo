package com.whzw.yz.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.whzw.yz.exception.GlobalException;
import com.whzw.yz.mapper.OrderLogMapper;
import com.whzw.yz.mapper.SeatOrderMapper;
import com.whzw.yz.pojo.SeatOrder;
import com.whzw.yz.result.CodeMsg;
import com.whzw.yz.util.LoginUtil;

@Service
public class CheckInService {

	@Autowired
	private SeatOrderMapper seatOrderMapper;

	@Autowired
	private OrderLogMapper orderLogMapper;

	@Autowired
	SeatOrderService seatOrderService;
	
	public boolean isSignIn(String orderId,HttpServletRequest req) {
		LoginUtil.LoginCheck(req);
		
		if(seatOrderMapper.getisSignIn(orderId)==1)
			return true;
		else
			return false;
	}

	/**
	 * 签到
	 * 
	 * @param qrCode
	 * @param req
	 * @return 签到时间
	 */
	@SuppressWarnings("deprecation")
	public Date signIn(String orderId, String qrCode, HttpServletRequest req) {
		// 这里暂时将二维码与座位id视为等价
		// TODO 二维码与座位id的映射

//		// 检查登录状态
		String studentId = LoginUtil.LoginCheck(req);

//		String studentId = "20164545";

		// 获取当前时间
		Date now = Calendar.getInstance().getTime();

		// 查找预定
		SeatOrder seatOrder = seatOrderMapper.findOneById(orderId);

		// 未查到预约
		if (seatOrder == null)
			throw new GlobalException(CodeMsg.NO_VALID_ORDER);

		// 该订单与该用户不匹配
		if (!seatOrder.getStudentId().equals(studentId))
			throw new GlobalException(CodeMsg.NO_VALID_ORDER);

		// 获取该预定的日期
		String orderTimeStr = seatOrder.getOrderCode().substring(0, 8);
		char timeQuantumNow = getTimeQuantum(now.getHours());

		// 不在服务时间内
		if (timeQuantumNow == 'E') {
			throw new GlobalException(CodeMsg.NOT_IN_TIME);
		}

		// 不在签到时间
		if (!(orderTimeStr.equals(new SimpleDateFormat("yyyyMMdd").format(now))
				&& timeQuantumNow == seatOrder.getTimeQuantun())) {
			throw new GlobalException(CodeMsg.NOT_IN_TIME);
		}

		// 创建超时时间对象
		Date timeOut = (Date) now.clone();

		// 设置超时时间
		switch (timeQuantumNow) {
		case 'M':
			timeOut.setHours(12);
			timeOut.setMinutes(0);
			timeOut.setSeconds(0);
			break;
		case 'A':
			timeOut.setHours(18);
			timeOut.setMinutes(0);
			timeOut.setSeconds(0);
			break;
		case 'N':
			timeOut.setHours(22);
			timeOut.setMinutes(0);
			timeOut.setSeconds(0);
			break;
		}

		// 设置数据库预约表中的开始时间
		orderLogMapper.updateStartTime(orderId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));

		//更新已签到状态
		seatOrderMapper.signIn(orderId);
		
		// 更新内存中 orderMap 的超时时间
		seatOrderService.getOrderMap().put(orderId, timeOut);
		return now;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public void signOut(String orderId, HttpServletRequest req) {
//		// 检查登录状态
		String studentId = LoginUtil.LoginCheck(req);

//		String studentId = "20164545";

		try {
			// 获取当前时间
			Date now = Calendar.getInstance().getTime();

			// 查找预约
			SeatOrder seatOrder = seatOrderMapper.findOneById(orderId);

			// 未查到预约
			if (seatOrder == null)
				throw new GlobalException(CodeMsg.ORDER_NOT_EXIST);

			// 该订单与该用户不匹配
			if (!seatOrder.getStudentId().equals(studentId))
				throw new GlobalException(CodeMsg.NO_VALID_ORDER);

			// 格式化结束时间
			String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);

			// 获取开始时间
			Date startTime = orderLogMapper.getStartTime(orderId);

			// 计算持续时间
			int last = (now.getHours() * 60 + now.getMinutes()) - (startTime.getHours() * 60 + startTime.getMinutes());

			// 删除预约表中的这条预约
			seatOrderMapper.deleteOrder(orderId);

			// 更新预约记录
			orderLogMapper.updateEndtimeStatusLast(orderId, endTime, last, "完成");

		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			throw e;
		}

		// 移除orderMap中该orderId对应的项
		seatOrderService.getOrderMap().remove(orderId);
	}

	/**
	 * 通过小时分析所处时间段
	 * 
	 * @param hours
	 * @return
	 */
	private char getTimeQuantum(int hours) {
		if (hours >= 7 && hours < 12) {
			return 'M';
		} else if (hours >= 12 && hours < 18) {
			return 'A';
		} else if (hours >= 18 && hours <= 22) {
			return 'N';
		} else {
			return 'E';
		}

	}

	/**
	 * 设置临时离开
	 * 
	 * @param req
	 * @param orderId
	 * @return
	 */
	public Date leaveForAMoment(HttpServletRequest req, String orderId) {
		String studentId = LoginUtil.LoginCheck(req);

		Date now = Calendar.getInstance().getTime();

		SeatOrder seatOrder = seatOrderMapper.findOneById(orderId);

		if (seatOrder == null || !seatOrder.getStudentId().equals(studentId)) {
			throw new GlobalException(CodeMsg.ORDER_NOT_EXIST);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.MINUTE, 15);
		Date newTimeOut = calendar.getTime();
		Date oldTimeOout = seatOrderService.getOrderMap().get("orderId");

		if (newTimeOut.after(oldTimeOout)) {
			newTimeOut = oldTimeOout;
		}
		// 数据库更改临时离开状态
		seatOrderMapper.setIsLeave(1, orderId);
		// 更改超时时间
		seatOrderService.getOrderMap().put(orderId, newTimeOut);
		return newTimeOut;
	}

	public void back(HttpServletRequest req, String orderId) {

		String studentId = LoginUtil.LoginCheck(req);
		SeatOrder seatOrder = seatOrderMapper.findOneById(orderId);

		if (seatOrder == null || !seatOrder.getStudentId().equals(studentId)) {
			throw new GlobalException(CodeMsg.ORDER_NOT_EXIST);
		}
		
		Date now = Calendar.getInstance().getTime();

		// 获取预定时间段
		char timeQuantumNow = getTimeQuantum(now.getHours());

		// 创建超时时间对象
		Date timeOut = (Date) now.clone();

		// 设置超时时间
		switch (timeQuantumNow) {
		case 'M':
			timeOut.setHours(12);
			timeOut.setMinutes(0);
			timeOut.setSeconds(0);
			break;
		case 'A':
			timeOut.setHours(18);
			timeOut.setMinutes(0);
			timeOut.setSeconds(0);
			break;
		case 'N':
			timeOut.setHours(22);
			timeOut.setMinutes(0);
			timeOut.setSeconds(0);
			break;
		}

		// 数据库取消临时离开状态
		seatOrderMapper.setIsLeave(0, orderId);
		// 更改超时时间
		seatOrderService.getOrderMap().put(orderId, timeOut);
	}

}
