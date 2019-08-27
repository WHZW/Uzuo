package com.whzw.yz.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whzw.yz.exception.GlobalException;
import com.whzw.yz.mapper.OrderLogMapper;
import com.whzw.yz.mapper.SeatOrderMapper;
import com.whzw.yz.pojo.SeatOrder;
import com.whzw.yz.result.CodeMsg;

@Service
public class CheckInService {

	@Autowired
	private SeatOrderMapper seatOrderMapper;

	@Autowired
	private OrderLogMapper orderLogMapper;

	@Autowired
	SeatOrderService seatOrderService;

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
//		String studentId = LoginUtil.LoginCheck(req);

		String studentId = "20164545";

		// 获取当前时间
		Date now = Calendar.getInstance().getTime();
		String nowStr = new SimpleDateFormat("yyyyMMdd").format(now);

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

		if (timeQuantumNow == 'E') {
			throw new GlobalException(CodeMsg.NOT_IN_TIME);
		}

		// 不在签到时间
		if (!(orderTimeStr.equals(nowStr) && timeQuantumNow == seatOrder.getTimeQuantun())) {
			throw new GlobalException(CodeMsg.NOT_IN_TIME);
		}

		Date timeOut = (Date) now.clone();

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

		System.out.println("timeOut " + timeOut);
		System.out.println("now " + now);

		// 更新内存中 orderMap 的超时时间
		seatOrderService.getOrderMap().put(orderId, timeOut);

		// 设置数据库预约表中的开始时间
		orderLogMapper.updateStartTime(orderId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));

		return now;
	}

	@SuppressWarnings("deprecation")
	public void signOut(String orderId, HttpServletRequest req) {
//		// 检查登录状态
//		String studentId = LoginUtil.LoginCheck(req);

		String studentId = "20164545";
		seatOrderService.getOrderMap();

		// 获取当前时间
		Date now = Calendar.getInstance().getTime();
		String nowStr = new SimpleDateFormat("yyyyMMdd").format(now);

		// 查找预约
		SeatOrder seatOrder = seatOrderMapper.findOneById(orderId);

		// 未查到预约
		if (seatOrder == null)
			throw new GlobalException(CodeMsg.NO_VALID_ORDER);

		// 该订单与该用户不匹配
		if (!seatOrder.getStudentId().equals(studentId))
			throw new GlobalException(CodeMsg.NO_VALID_ORDER);
		
		//格式化结束时间
		String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
		
		//获取开始时间
		Date startTime = orderLogMapper.getStartTime(orderId);
		
		//计算持续时间
		int last = (now.getHours()*60+ now.getMinutes())-(startTime.getHours()*60+startTime.getMinutes());
		
		
		
		//删除预约表中的这条预约
		seatOrderMapper.deleteOrder(orderId);
		
		//更新预约记录
		orderLogMapper.updateEndtimeStatusLast(orderId, endTime , last, "完成");

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

}
