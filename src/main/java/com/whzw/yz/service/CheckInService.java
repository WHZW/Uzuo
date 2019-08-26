package com.whzw.yz.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.whzw.yz.util.LoginUtil;

@Service
public class CheckInService {

	public void signIn(String qrCode, HttpServletRequest req) {
		// 这里暂时将二维码与座位id视为等价
		// TODO 二维码与座位id的映射
		LoginUtil.LoginCheck(req);
	}

}
