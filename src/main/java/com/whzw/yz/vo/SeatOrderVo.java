package com.whzw.yz.vo;

import java.text.SimpleDateFormat;

import com.whzw.yz.pojo.SeatOrder;

/**
 * 预约信息
 * @author wubn
 *
 */
public class SeatOrderVo{
	
	private String orderId;

	private String studentId;
	
	private String clroomId;
	
	private String tableId;

	private String seatId;

	private String orderTime;

	private String date;
	
	private char timeQuantum;
	
	private String desc;
	
	private String isSignin = "0";
	
	public SeatOrderVo() {}
	
	public SeatOrderVo(SeatOrder seatOrder) {
		this.orderId = seatOrder.getId();
		this.seatId = seatOrder.getSeatId();
		this.studentId = seatOrder.getStudentId();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.orderTime = format.format(seatOrder.getOrderTime());
		format.applyPattern("yyyy-MM-dd");
		this.date = format.format(seatOrder.getDate());
		this.timeQuantum = seatOrder.getTimeQuantun();
	}
	

	public SeatOrderVo(String orderId, String studentId, String seatId, String orderTime, String date) {
		super();
		this.orderId = orderId;
		this.studentId = studentId;
		this.seatId = seatId;
		this.orderTime = orderTime;
		this.date = date;
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

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
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

	public char getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(char timeQuantum) {
		this.timeQuantum = timeQuantum;
	}	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getIsSignin() {
		return isSignin;
	}

	public void setIsSignin(String isSignin) {
		this.isSignin = isSignin;
	}
	
	

	public String getClroomId() {
		return clroomId;
	}

	public void setClroomId(String roomId) {
		this.clroomId = roomId;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "SeatOrderVo [orderId=" + orderId + ", studentId=" + studentId + ", roomId=" + clroomId + ", tableId="
				+ tableId + ", seatId=" + seatId + ", orderTime=" + orderTime + ", date=" + date + ", timeQuantum="
				+ timeQuantum + ", isSignin=" + isSignin + "]";
	}	
	
}
