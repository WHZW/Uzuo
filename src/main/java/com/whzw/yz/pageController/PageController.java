package com.whzw.yz.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/")
	public String home() {
		return "home.html";
	}
	
	@GetMapping("/getCenter")
	public String getCenter() {
		return "center.html";
	}
	
	@GetMapping("/getLogin")
	public String getLogin() {
		return "login.html";
	}
	
	@GetMapping("/getPlan")
	public String getPlan() {
		return "plan.html";
	}
	
	@GetMapping("/getSelect")
	public String getSelect() {
		return "select.html";
	}
	
	@GetMapping("/getSubscribe")
	public String getSubscribe() {
		return "subscribe.html";
	}
	
	@GetMapping("/getSubscribeOK")
	public String getSubscribeOK() {
		return "subscribeOK.html";
	}
}
