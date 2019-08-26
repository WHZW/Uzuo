package com.whzw.yz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.whzw.yz.pojo.Clroom;

/**
 * 教室Mapper
 * 
 * @author zzy
 *
 */
public interface ClroomMapper {

	/**
	 * 添加一个教室并返回自增id
	 * 
	 * @author zzy
	 * @param clroom
	 */
	@Insert("insert into `clroom`(`name`,`floor`,`desc`) values(#{clr.name},#{clr.floor},#{clr.desc})")
	@Options(useGeneratedKeys = true, keyProperty = "clroomId")
	public void addOne(@Param("clr") Clroom clroom);

	/**
	 * 通过id查找一个教室
	 * 
	 * @author zzy
	 * @param clroomId
	 * @return
	 */
	@Select("select * from `clroom` where `clroom_id`=#{id}")
	@Results({
			@Result(column = "clroom_id", property = "tables", many = @Many(select = "com.whzw.yz.mapper.TableMapper.findAllByClroomId")) })
	public Clroom findOneById(@Param("id") String clroomId);

	
}
