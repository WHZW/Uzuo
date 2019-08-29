package com.whzw.yz.vo;

/**
 * 预定
 * @author wubn
 *
 */
public class OrderVo {
	
	private int year;
	
	private int month;
	
	private int day;
	
	private char timeQuantum;
	
	private String seatId = "";
	
	public OrderVo() {
		super();
	}

	public OrderVo(int year, int month, int day, char timeQuantum) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.timeQuantum = timeQuantum;
	}



	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public char getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(char timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	@Override
	public String toString() {
		return "OrderVo [year=" + year + ", month=" + month + ", day=" + day + ", timeQuantum=" + timeQuantum
				+ ", seatId=" + seatId + "]";
	}
	
	
	
	

}
