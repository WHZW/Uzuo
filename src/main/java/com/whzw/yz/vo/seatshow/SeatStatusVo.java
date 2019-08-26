package com.whzw.yz.vo.seatshow;

public class SeatStatusVo {

	private String seatId;

	private int status;

	public SeatStatusVo() {
		super();
	}

	public SeatStatusVo(String seatId, int status) {
		this.seatId = seatId;
		this.status = status;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SeatStatusVo [seatId=" + seatId + ", status=" + status + "]";
	}

}
