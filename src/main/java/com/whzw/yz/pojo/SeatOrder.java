package com.whzw.yz.pojo;

import java.util.Date;

/**
 *  
 * @author zzy
 *@author WuBN
 */
public class SeatOrder {

	private String id;

	private String studentId;

	private String seatId;

	private Date OrderTime;

	private Date date;

	/**
	 * 时间段
	 */
	private char timeQuantun;

	/**
	 * 编码
	 */
	private String orderCode;

	public SeatOrder() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public Date getOrderTime() {
		return OrderTime;
	}

	public void setOrderTime(Date orderTime) {
		OrderTime = orderTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getTimeQuantun() {
		return timeQuantun;
	}

	public void setTimeQuantun(char timeQuantun) {
		this.timeQuantun = timeQuantun;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	
}