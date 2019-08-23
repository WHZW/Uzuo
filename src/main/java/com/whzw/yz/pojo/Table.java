package com.whzw.yz.pojo;

import java.util.List;

public class Table {

	private String id;

	private clroom clroom;

	private int row;

	private int col;

	private List<Seat> seats;

	public Table() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public clroom getClroom() {
		return clroom;
	}

	public void setClroom(clroom clroom) {
		this.clroom = clroom;
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
		return "Table [id=" + id + ", clroom=" + clroom + ", row=" + row + ", col=" + col + ", seats=" + seats + "]";
	}

}
