package com.whzw.yz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whzw.yz.mapper.OrderLogMapper;
import com.whzw.yz.mapper.SeatOrderMapper;
import com.whzw.yz.mapper.StudentMapper;
import com.whzw.yz.pojo.Student;
import com.whzw.yz.util.LoginUtil;
import com.whzw.yz.vo.OrderLogVo;
import com.whzw.yz.vo.SeatOrderVo;

/**
 * 获取用户信息、预约记录、历史记录
 * 
 * @author wubn
 *
 */
@Service
public class UserManagerService {

	@Autowired
	StudentMapper studentMapper;

	@Autowired
	SeatOrderMapper seatOrderMapper;

	@Autowired
	OrderLogMapper orderLogMapper;

	/**
	 * 获取用户基本信息
	 * 
	 * @return
	 */
	public Student getUserInfo(HttpServletRequest req) {
		String studentId = LoginUtil.LoginCheck(req);
//		String studentId = "20164545";
		Student student = studentMapper.getStudentInfo(studentId);
		return student;
	}

	/**
	 * 获取预定记录
	 * 
	 * @param request
	 * @return
	 */
	public List<SeatOrderVo> getSeatOrders(HttpServletRequest request) {
//		String studentId = LoginUtil.LoginCheck(request);
		String studentId = "20164545";
		List<SeatOrderVo> orders = seatOrderMapper.getOrderInfoByStudentId(studentId);
		return orders;
	}

	/**
	 * 获取预定历史
	 * 
	 * @param request
	 * @return
	 */
	public List<OrderLogVo> getHistory(HttpServletRequest request) {
		String studentId = LoginUtil.LoginCheck(request);
//		String studentId = "20164545";
		List<OrderLogVo> logs = orderLogMapper.getHistory(studentId);
		return logs;
	}

}
