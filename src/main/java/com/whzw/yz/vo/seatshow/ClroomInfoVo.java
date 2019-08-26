package com.whzw.yz.vo.seatshow;

public class ClroomInfoVo {
	private String clroomId;

	private String name;

	private String desc;

	private int floor;

	private int seatNum;

	/**
	 * 空闲座位数量
	 */
	private int freeNum;

	public ClroomInfoVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getClroomId() {
		return clroomId;
	}

	public void setClroomId(String clroomId) {
		this.clroomId = clroomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public int getFreeNum() {
		return freeNum;
	}

	public void setFreeNum(int freeNum) {
		this.freeNum = freeNum;
	}

	@Override
	public String toString() {
		return "ClroomInfoVo [clroomId=" + clroomId + ", clroomName=" + name + ", desc=" + desc + ", floor=" + floor
				+ ", seatNum=" + seatNum + ", freeNum=" + freeNum + "]";
	}

}
