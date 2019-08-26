package com.whzw.yz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whzw.yz.service.CheckInService;

@Controller
@RequestMapping("/checkin")
public class CheckInController {
	
	@Autowired
	private CheckInService checkInService;
	
	@GetMapping("/signin")
	public void signIn(@RequestParam("qrCode") String qrCode,HttpServletRequest req) {
		checkInService.signIn(qrCode,req);
	}
	
	@GetMapping("leave")
	public void leave() {
		
	}
}
