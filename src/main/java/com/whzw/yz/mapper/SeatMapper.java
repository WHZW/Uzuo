package com.whzw.yz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whzw.yz.pojo.Seat;

/**
 * 座位Mapper
 * 
 * @author zzy
 *
 */
@Mapper
public interface SeatMapper {

	/**
	 * 插入一个座位并返回自增id
	 * 
	 * @author zzy
	 * @param seat
	 */
	@Insert("insert into `seat`(`table_id`,`location`,`row`,`col`,`desc`) values(#{s.tableId},#{s.location},#{s.row},#{s.col},#{s.desc})")
	@Options(useGeneratedKeys = true, keyProperty = "seatId")
	public void addOne(@Param("s") Seat seat);

	@Select("select * from `seat` where table_id=#{tid}")
	public List<Seat> findAllByTableId(@Param("tid") String tableId);
	
	@Select("select `seat_id` from `seat` where table_id=#{tid}")
	public List<String> findAllIdByTableId(@Param("tid") String tableId);
}
