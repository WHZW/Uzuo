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

	private String studentName;
	
	private String clroomName;
	
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.orderTime = format.format(seatOrder.getOrderTime());
		format.applyPattern("yyyy-MM-dd");
		this.date = format.format(seatOrder.getDate());
		this.timeQuantum = seatOrder.getTimeQuantun();
	}

	public String getId() {
		return orderId;
	}

	public void setId(String id) {
		this.orderId = id;
	}
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClroomName() {
		return clroomName;
	}

	public void setClroomName(String clroomName) {
		this.clroomName = clroomName;
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
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
