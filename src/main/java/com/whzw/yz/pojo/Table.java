package com.whzw.yz.pojo;

import java.util.List;

/**
 * 
 * @author zheng
 * @author WuBN
 */
public class Table {

	private String tableId;

	private String clroomId;

	private int row;

	private int col;

	private List<Seat> seats;

	public Table() {
		super();
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getClroomId() {
		return clroomId;
	}

	public void setClroomId(String clroomId) {
		this.clroomId = clroomId;
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Table [tableId=" + tableId + ", clroomId=" + clroomId + ", row=" + row + ", col=" + col + ", seats="
				+ seats + "]";
	}

}
