package com.whzw.yz.pojo;

import java.util.Date;

public class SeatOrder {

	private String id;

	private Student student;

	private Seat seat;

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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
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

	@Override
	public String toString() {
		return "SeatOrder [id=" + id + ", student=" + student + ", OrderTime=" + OrderTime + ", date=" + date
				+ ", timeQuantun=" + timeQuantun + ", orderCode=" + orderCode + "]";
	}

}
