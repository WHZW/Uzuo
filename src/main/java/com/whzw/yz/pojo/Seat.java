package com.whzw.yz.pojo;

/**
 * 
 * @author zzy
 * @author WuBN
 */
public class Seat {

	private String seatId;

	private String tableId;

	/**
	 * 位置 数据库里存的是枚举类型A B C D
	 */
	private char location;

	private int row;

	private int col;

	private String desc;

	public Seat() {
		super();
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public char getLocation() {
		return location;
	}

	public void setLocation(char location) {
		this.location = location;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", tableId=" + tableId + ", location=" + location + ", row=" + row + ", col="
				+ col + ", desc=" + desc + "]";
	}

}
