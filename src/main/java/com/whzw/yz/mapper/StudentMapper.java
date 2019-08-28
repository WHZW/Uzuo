package com.whzw.yz.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whzw.yz.pojo.Student;

/**
 * 学生信息表
 * @author wubn
 *
 */
@Mapper
public interface StudentMapper {

	@Select("select * from student where student_id = #{studentId}")
	public Student getStudentInfo(String studentId);
	
	@Update("update student set integral = integral - 10 where student_id = #{studentId}")
	public void duduct(String studentId);

	@Select("select integral from student where student_id = #{studentId}")
	public int getIntegralByStudentId(String studentId);

}
