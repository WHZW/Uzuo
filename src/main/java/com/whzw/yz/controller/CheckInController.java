package com.whzw.yz.controller;

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

	/**
	 * 签到
	 * 
	 * @author zzy
	 * @param qrCode
	 * @param req
	 */
	@GetMapping("/signin")
	@ResponseBody
	public Result<Date> signIn(@RequestParam("orderId") String orderId, @RequestParam("qrCode") String qrCode,
			HttpServletRequest req) {
		Date startTime = checkInService.signIn(orderId, qrCode, req);

		return Result.success(startTime);
	}

	/**
	 * 签退
	 */
	@GetMapping("signOut")
	public void signOut(HttpServletRequest req) {
//		checkInService.signOut(req);
	}
}
