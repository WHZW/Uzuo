package com.whzw.yz.pojo;

public class Seat {

	private String id;

	private clroom clroom;

	private Table table;

	private String desc;

	public Seat() {
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

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", clroom=" + clroom + ", table=" + table + ", desc=" + desc + "]";
	}

}
