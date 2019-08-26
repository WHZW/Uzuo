package com.whzw.yz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.whzw.yz.pojo.OrderLog;

public interface OrderLogMapper {

	@Insert("insert into order_log(order_id, student_id, seat_id, order_time) "
			+ "values(#{orderLog.orderId}, #{orderLog.studentId}, #{orderLog.seatId}, #{orderLog.orderTime})")
	public void addLog(@Param("orderLog")OrderLog orderLog);

	@Update("update order_log set status = #{status} where id = #{id}")
	public void updateStatus(@Param("id")String id, @Param("status")String status);

}
