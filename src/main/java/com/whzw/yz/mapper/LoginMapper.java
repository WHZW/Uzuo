package com.whzw.yz.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whzw.yz.vo.LoginVo;

@Mapper
public interface LoginMapper {

	@Select("select `student_id`,`password` from `student_login` where student_id=#{id}")
	public LoginVo getStudent(@Param("id") String id);

}
