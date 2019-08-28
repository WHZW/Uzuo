package com.whzw.yz.pojo;
/**
 * 
 * @author zzy
 * @author WuBN
 */
public class Student {

	private String studentId;

	private String name;

	private String gender;

	private String college;

	private String subject;

	private String phone;
	
	private int integral;

	public Student() {
		super();
	}

	public String getId() {
		return studentId;
	}

	public void setId(String id) {
		this.studentId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	@Override
	public String toString() {
		return "Student [id=" + studentId + ", name=" + name + ", gender=" + gender + ", college=" + college + ", subject="
				+ subject + ", phone=" + phone + "]";
	}

}
