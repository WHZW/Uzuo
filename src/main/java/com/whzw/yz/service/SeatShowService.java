package com.whzw.yz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whzw.yz.mapper.ClroomMapper;
import com.whzw.yz.pojo.Clroom;

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

	/**
	 * 查找指定ID为clroomId的教室的所有座位
	 * 
	 * @return
	 * @author zzy
	 */
	public Clroom showClroomSeats(String clroomId) {
		
		return clroomMapper.findOneById(clroomId);
	}
}
