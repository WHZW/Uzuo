package com.whzw.yz.pojo;

import java.util.Date;

public class StudentLogin {

	private String studentId;

	private String password;

	private String status;

	private Date freezeTime;

	public StudentLogin() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
	}

	@Override
	public String toString() {
		return "StudentLogin [studentId=" + studentId + ", password=" + password + ", status=" + status
				+ ", freezeTime=" + freezeTime + "]";
	}

}
