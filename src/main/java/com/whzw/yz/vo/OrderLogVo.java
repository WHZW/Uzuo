package com.whzw.yz.vo;

import java.text.SimpleDateFormat;

import com.whzw.yz.pojo.OrderLog;

public class OrderLogVo {
	
	private String orderId;

	private String studentId;

	private String seatId;
	
	private String orderTime;

	private String startTime;

	private String endTime;

	private int last;

	private String status;
	
	public OrderLogVo() {
		super();
	}

	public OrderLogVo(OrderLog orderLog) {
		this.orderId = orderLog.getId();
		this.seatId = orderLog.getSeatId();
		this.studentId = orderLog.getStudentId();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.orderTime = format.format(orderLog.getOrderTime());
		format.applyPattern("yyyy-MM-dd");
		this.startTime = format.format(orderLog.getStartTime());
		this.endTime = format.format(orderLog.getEndTime());
		this.last = orderLog.getLast();
		this.status = orderLog.getStatus();
		
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderLogVo [orderId=" + orderId + ", studentId=" + studentId + ", seatId=" + seatId + ", orderTime="
				+ orderTime + ", startTime=" + startTime + ", endTime=" + endTime + ", last=" + last + ", status="
				+ status + "]";
	}
	
	

	
}
