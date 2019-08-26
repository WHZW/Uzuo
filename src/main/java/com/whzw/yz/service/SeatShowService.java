package com.whzw.yz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whzw.yz.mapper.ClroomMapper;
import com.whzw.yz.mapper.SeatOrderMapper;
import com.whzw.yz.pojo.Clroom;
import com.whzw.yz.pojo.Seat;
import com.whzw.yz.pojo.Table;
import com.whzw.yz.vo.seatshow.ClroomWithSeatstatus;
import com.whzw.yz.vo.seatshow.SeatStatusVo;

/**
 * 查看图书馆自习室座位状态
 * 
 * @author zzy
 *
 */
@Service
public class SeatShowService {

	@Autowired
	private ClroomMapper clroomMapper;

	@Autowired
	private SeatOrderMapper seatOrderMapper;

	/**
	 * 查找指定ID为clroomId的教室的所有座位
	 * 
	 * @return
	 * @author zzy
	 */
	public ClroomWithSeatstatus showClroomSeatsWithStatus(String clroomId, Date date, char timeQuantum) {

		ClroomWithSeatstatus clroomWithSeatstatus = new ClroomWithSeatstatus();
		// 获取教室信息对象（教室--》桌子列表--》座位列表）
		Clroom clroom = clroomMapper.findOneById(clroomId);
		clroomWithSeatstatus.setClroom(clroom);

		SeatStatusVo seatStatusVo = null;
		String seatId = null;

		List<SeatStatusVo> seatStatusVos = new ArrayList<>();
		// 遍历该教室所有座位
		for (Table table : clroom.getTables()) {
			for (Seat seat : table.getSeats()) {
				// 获得座位的id
				seatId = seat.getSeatId();

				// 从数据库查找该座位这一时间段是否有预订信息
				Seat rightSeat = seatOrderMapper.findOne(seatId, date, timeQuantum);

				// 为座位状态对象设置座位id和其对应的状态 0空闲
				seatStatusVo = new SeatStatusVo();
				seatStatusVo.setSeatId(seatId);
				if (rightSeat == null) {
					seatStatusVo.setStatus(0);
				} else {
					seatStatusVo.setStatus(1);
				}

				// 加入到座位状态列表中
				seatStatusVos.add(seatStatusVo);
			}
		}

		clroomWithSeatstatus.setSeatStatusVos(seatStatusVos);

		return clroomWithSeatstatus;
	}

}
