package com.whzw.yz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.whzw.yz.pojo.Table;

/**
 * 座位Mapper
 * 
 * @author zzy
 *
 */
public interface TableMapper {

	/**
	 * 插入一张桌子并返回自增id
	 * 
	 * @author zzy
	 * @param table
	 */
	@Insert("insert into `table`(`clroom_id`,`row`,`col`) values(#{t.clroomId},#{t.row},#{t.col})")
	@Options(useGeneratedKeys = true, keyProperty = "tableId")
	public void addOne(@Param("t") Table table);

	@Select("select * from `table` where clroom_id=#{cid}")
	@Results({
			@Result(column = "table_id", property = "seats", many = @Many(select = "com.whzw.yz.mapper.SeatMapper.fingSeatsByTableId")) })
	public List<Table> findAllByClroomId(@Param("cid") String clroomId);
}
