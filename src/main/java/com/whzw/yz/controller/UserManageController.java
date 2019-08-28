package com.whzw.yz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.pojo.Student;
import com.whzw.yz.result.Result;
import com.whzw.yz.service.UserManagerService;
import com.whzw.yz.vo.OrderLogVo;
import com.whzw.yz.vo.SeatOrderVo;

/**
 * 获取用户信息和预约记录
 * @author wubn
 *
 */
@Controller
@RequestMapping("/userManager")
public class UserManageController {
	
	@Autowired
	UserManagerService userManagerService;
	
	/**
	 * 获取用户信息
	 * @param request
	 * @return
	 */
	@GetMapping("/getInfo")
	@ResponseBody
	public Result<Student> getUserInfo(HttpServletRequest request){
		Student studentInfo = userManagerService.getUserInfo(request);
		System.out.println(studentInfo);
		return Result.success(studentInfo);
	}
	
	/**
	 * 获取预定记录
	 * @param request
	 * @return
	 */
	@GetMapping("/getOrders")
	@ResponseBody
	public Result<List<SeatOrderVo>> getOrders(HttpServletRequest request){
		List<SeatOrderVo> seatOrders = userManagerService.getSeatOrders(request);
		System.out.println(seatOrders);
		return Result.success(seatOrders);
	}
	
	/**
	 * 获取历史记录
	 * @param request
	 * @return
	 */
	@GetMapping("/getHistory")
	@ResponseBody
	public Result<List<OrderLogVo>> getHistory(HttpServletRequest request){
		List<OrderLogVo> logs = userManagerService.getHistory(request);
		return Result.success(logs);
	}
	
	
	
}
