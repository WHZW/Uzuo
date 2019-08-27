package com.whzw.yz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whzw.yz.mapper.ClroomMapper;
import com.whzw.yz.mapper.SeatMapper;
import com.whzw.yz.mapper.TableMapper;
import com.whzw.yz.pojo.Clroom;
import com.whzw.yz.pojo.Seat;
import com.whzw.yz.pojo.Table;

@Service
public class ClroomManageService {

	@Autowired
	private ClroomMapper clroomMapper;

	@Autowired
	private TableMapper tableMapper;

	@Autowired
	private SeatMapper seatMapper;

	/**
	 * 增加一个教室
	 * 
	 * @author zzy
	 * @param clroom
	 * @return
	 */
	@Transactional
	public boolean addOneClass(Clroom clroom) {

		Table table = null;
		List<Seat> seats = null;
		Seat seat = null;
		String clroomId = null;
		String tableId = null;
		// 插入教室表
		clroomMapper.addOne(clroom);
		// 获取自增主键
		clroomId = clroom.getClroomId();
		List<Table> tables = clroom.getTables();
		for (int i = 0; i < tables.size(); i++) {
			table = tables.get(i);
			table.setClroomId(clroomId);
			// 插入桌子表
			tableMapper.addOne(table);
			// 获取自增主键
			tableId = table.getTableId();
			seats = table.getSeats();
			for (int j = 0; j < seats.size(); j++) {
				seat = seats.get(j);
				seat.setTableId(tableId);
				// 插入座位表
				seatMapper.addOne(seat);
			}
		}
		return true;
	}
}
