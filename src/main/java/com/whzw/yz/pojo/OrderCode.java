package com.whzw.yz.pojo;

public class OrderCode {

	private String year;

	private String month;

	private String day;

	private char timeQuantum;

	private String seatId;

	public OrderCode(String year, String month, String day, char timeQuantum, String seatId) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.timeQuantum = timeQuantum;
		this.seatId = seatId;
	}

	public OrderCode() {
		super();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
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
		return "OrderCode [year=" + year + ", month=" + month + ", day=" + day + ", timeQuantum=" + timeQuantum
				+ ", seatId=" + seatId + "]";
	}

}
