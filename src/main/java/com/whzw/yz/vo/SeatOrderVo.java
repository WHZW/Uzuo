package com.whzw.yz.vo;

import java.text.SimpleDateFormat;

import com.whzw.yz.pojo.SeatOrder;

public class SeatOrderVo{
	
	private String orderId;

	private String studentId;

	private String seatId;

	private String orderTime;

	private String date;
	
	public SeatOrderVo(SeatOrder seatOrder) {
		this.orderId = seatOrder.getId();
		this.seatId = seatOrder.getSeatId();
		this.studentId = seatOrder.getStudentId();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.orderTime = format.format(seatOrder.getOrderTime());
		format.applyPattern("yyyy-MM-dd");
		this.date = format.format(seatOrder.getDate());
	}

	public String getId() {
		return orderId;
	}

	public void setId(String id) {
		this.orderId = id;
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

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
