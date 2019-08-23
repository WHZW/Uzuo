package com.whzw.yz.pojo;

import java.util.Date;

public class OrderLog {

	private String id;

	private Student student;

	private Seat seat;

	private Date startTime;

	private Date endTime;

	private int last;

	private boolean timeOut;

	public OrderLog() {
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

	public boolean isTimeOut() {
		return timeOut;
	}

	public void setTimeOut(boolean timeOut) {
		this.timeOut = timeOut;
	}

	@Override
	public String toString() {
		return "OrderLog [id=" + id + ", student=" + student + ", seat=" + seat + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", last=" + last + ", timeOut=" + timeOut + "]";
	}

}
