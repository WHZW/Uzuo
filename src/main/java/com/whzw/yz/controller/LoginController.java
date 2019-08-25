package com.whzw.yz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.Result;
import com.whzw.yz.service.LoginService;
import com.whzw.yz.vo.LoginVo;

/**
 * 学生用户登录控制器
 * 
 * @author zzy
 * @author WuBN
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public String getLoginPage() {
		return "login.html";
	}

	@PostMapping("/do_login")
	@ResponseBody
	public Result<Boolean> login(@RequestBody LoginVo loginVo, HttpServletRequest req, HttpServletResponse resp) {
		boolean isSucceed = loginService.doLogin(loginVo, req, resp);
		return Result.success(isSucceed);
	}

}
