package com.whzw.yz.pojo;

import java.util.Date;
/**
 * 
 * @author zzy
 * @author WuBN
 */
public class OrderLog {

	private String id;
	
	private String orderId;

	private String studentId;

	private String seatId;
	
	private Date orderTime;
	
	private Date date;
	
	private char timeQuantum;

	private Date startTime;

	private Date endTime;

	private int last;

	private String status;

	public OrderLog() {
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(char timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	@Override
	public String toString() {
		return "OrderLog [id=" + id + ", orderId=" + orderId + ", studentId=" + studentId + ", seatId=" + seatId
				+ ", orderTime=" + orderTime + ", date=" + date + ", timeQuantum=" + timeQuantum + ", startTime="
				+ startTime + ", endTime=" + endTime + ", last=" + last + ", status=" + status + "]";
	}

}
