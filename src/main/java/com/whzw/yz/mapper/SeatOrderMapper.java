package com.whzw.yz.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whzw.yz.pojo.Seat;

/**
 * 座位预定表
 * 
 * @author zheng
 *
 */
@Mapper
public interface SeatOrderMapper {

	@Select("select * from `seat_order` where date=#{date} and seat_id=#{sid} and time_quantum=#{tq}")
	public Seat findOne(@Param("sid") String seatId, @Param("date") Date date, @Param("tq") char timeQuantum);
	
}
