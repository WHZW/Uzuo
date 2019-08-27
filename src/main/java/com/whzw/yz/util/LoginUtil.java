package com.whzw.yz.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.whzw.yz.exception.GlobalException;
import com.whzw.yz.result.CodeMsg;

public class LoginUtil {

	/**
	 * 如果用户在线则返回用户id 如果用户不在线则抛出session_error异常
	 * 
	 * @param req
	 * @return
	 */
	public static String LoginCheck(HttpServletRequest req) {

		Cookie[] cookies = req.getCookies();
		String cookieToken = null;

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				cookieToken = cookie.getValue();
			}
		}

		if (cookieToken == null)
			throw new GlobalException(CodeMsg.SESSION_ERROR);

		String sessionToken = null;
		HttpSession session = req.getSession();
		sessionToken = String.valueOf(session.getAttribute("token"));

		if (sessionToken == null)
			throw new GlobalException(CodeMsg.SESSION_ERROR);

		if (!sessionToken.equals(sessionToken)) {
			throw new GlobalException(CodeMsg.SESSION_ERROR);
		}
		return String.valueOf(session.getAttribute("studentId"));
	}
}
