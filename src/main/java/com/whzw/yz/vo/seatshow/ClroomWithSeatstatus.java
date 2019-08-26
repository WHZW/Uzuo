package com.whzw.yz.vo.seatshow;

import java.util.List;
import java.util.Map;

import com.whzw.yz.pojo.Clroom;

public class ClroomWithSeatstatus {

	private Clroom clroom;

	private List<SeatStatusVo> seatStatusVos;

	public ClroomWithSeatstatus() {
		super();
	}

	public Clroom getClroom() {
		return clroom;
	}

	public void setClroom(Clroom clroom) {
		this.clroom = clroom;
	}

	public List<SeatStatusVo> getSeatStatusVos() {
		return seatStatusVos;
	}

	public void setSeatStatusVos(List<SeatStatusVo> seatStatusVos) {
		this.seatStatusVos = seatStatusVos;
	}

	@Override
	public String toString() {
		return "ClroomWithSeatstatus [clroom=" + clroom + ", seatStatusVos=" + seatStatusVos + "]";
	}

}
