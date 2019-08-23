package com.whzw.yz.pojo;

/**
 * 教室
 * 
 * @author zheng
 *
 */
public class clroom {

	private String id;

	private String name;

	private int floor;

	private String desc;

	public clroom() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "clroom [id=" + id + ", name=" + name + ", floor=" + floor + ", desc=" + desc + "]";
	}

}
