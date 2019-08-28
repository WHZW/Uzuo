package com.whzw.yz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whzw.yz.pojo.StudentLogin;
import com.whzw.yz.vo.LoginVo;
/**
 * 学生登录Mapper
 * @author zzy
 * @author WuBN
 */
@Mapper
public interface LoginMapper {

	@Select("select `student_id`,`password` from `student_login` where student_id=#{id}")
	public LoginVo getStudent(@Param("id") String id);

	@Insert("insert into student_login(student_id, password) values(#{sl.studentId}, #{sl.password})")
	public void addStudent(@Param("sl")StudentLogin studentLogin);
	
	

}
