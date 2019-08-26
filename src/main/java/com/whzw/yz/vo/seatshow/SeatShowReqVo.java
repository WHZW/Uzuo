package com.whzw.yz.vo.seatshow;

/**
 * 查找座位通信临时变量
 * 
 * @author zzy
 *
 */
public class SeatShowReqVo {

	private String year;

	private String month;

	private String day;

	private char timeQuantum;

	private String clroomId;

	public SeatShowReqVo() {
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

	public String getClroomId() {
		return clroomId;
	}

	public void setClroomId(String clroomId) {
		this.clroomId = clroomId;
	}

	@Override
	public String toString() {
		return "SeatShowVo [year=" + year + ", month=" + month + ", day=" + day + ", timeQuantum=" + timeQuantum
				+ ", clroomId=" + clroomId + "]";
	}

}
