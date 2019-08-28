package com.whzw.yz.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whzw.yz.exception.GlobalException;
import com.whzw.yz.mapper.LoginMapper;
import com.whzw.yz.result.CodeMsg;
import com.whzw.yz.util.LoginUtil;
import com.whzw.yz.util.UUIDUtil;
import com.whzw.yz.vo.LoginVo;

/**
 * 
 * @author zheng
 * @author WuBN
 *
 */
@Service
public class LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	public boolean doLogin(LoginVo loginVo, HttpServletRequest req, HttpServletResponse resp) {
		
		if(LoginUtil.LoginCheck(req)!=null) {
//			throw new GlobalException(CodeMsg.REPEAT_LOGIN)
		}
		
		String id = loginVo.getStudentId();
		String password = loginVo.getPassword();
		LoginVo loginVoDb = loginMapper.getStudent(id);
		HttpSession session = req.getSession();
		
		
		if(loginVoDb==null) {
			throw new GlobalException(CodeMsg.STUDENT_NOT_EXIST);
		}
		
		if(!password.equals(loginVoDb.getPassword())) {
			throw new GlobalException(CodeMsg.PASSWORD_ERROR);
		}
		
		String uuid = UUIDUtil.uuid();
		session.setAttribute("token", uuid);
		session.setAttribute("studentId", loginVo.getStudentId());
		Cookie cookie = new Cookie("token", uuid);
		cookie.setPath("/");
		cookie.setMaxAge(300);
		resp.addCookie(cookie);
		return true;
	}

	
	
}
