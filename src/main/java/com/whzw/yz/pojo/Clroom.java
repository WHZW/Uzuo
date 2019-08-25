package com.whzw.yz.pojo;

import java.util.List;

/**
 * 
 * @author zzy
 * @author WuBN
 */
public class Clroom {

	private String clroomId;

	private String name;

	private int floor;

	private String desc;

	private List<Table> tables;

	public Clroom() {
		super();
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

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	@Override
	public String toString() {
		return "Clroom [clroomId=" + clroomId + ", name=" + name + ", floor=" + floor + ", desc=" + desc + ", tables="
				+ tables + "]";
	}

}
