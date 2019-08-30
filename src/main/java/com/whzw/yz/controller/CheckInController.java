package com.whzw.yz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.Result;
import com.whzw.yz.service.CheckInService;

/**
 * 打卡相关操作的控制器
 * 
 * @author zzy
 */
@Controller
@RequestMapping("/library/checkin")
public class CheckInController {

	@Autowired
	private CheckInService checkInService;
	
	@GetMapping("/issignin")
	@ResponseBody
	public Result<Boolean> isSignIn(@RequestParam("orderId") String orderId, HttpServletRequest req){
		return Result.success(checkInService.isSignIn(orderId, req));
	}

	/**
	 * 签到
	 * 
	 * @author zzy
	 * @param qrCode
	 * @param req
	 */
	@GetMapping("/signin")
	@ResponseBody
	public Result<String> signIn(@RequestParam("orderId") String orderId,HttpServletRequest req) {
		Date startTime = checkInService.signIn(orderId, req);
		String startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
		
		return Result.success(startTimeStr);
	}
	

	/**
	 * 签退
	 * @return 
	 */
	@GetMapping("signout")
	@ResponseBody
	public Result<Boolean> signOut(@RequestParam("orderId") String orderId, HttpServletRequest req) {
		checkInService.signOut(orderId, req);
		return Result.success(true);
	}
	
//	public Result<String> leaveForAMoment(HttpServletRequest req){
//		
//		CheckInService.leaveForAMoment(req);
//		return null;
//	}
}
