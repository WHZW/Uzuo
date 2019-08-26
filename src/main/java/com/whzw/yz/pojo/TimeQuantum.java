package com.whzw.yz.pojo;

public enum TimeQuantum {
	
	M(1, 'M'), A(2, 'A'), N(3, 'N');
	
	private int state;
	
	private char info;

	private TimeQuantum(int state, char info) {
		this.state = state;
		this.info = info;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public char getInfo() {
		return info;
	}

	public void setInfo(char info) {
		this.info = info;
	}
	
	
	

}
