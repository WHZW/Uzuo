package com.whzw.yz.vo;

public class LoginVo {

	private String studentId;

	private String password;

	public LoginVo() {
		super();
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginVo [studentId=" + studentId + ", password=" + password + "]";
	}


}
