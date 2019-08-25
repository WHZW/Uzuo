package com.whzw.yz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.whzw.yz.pojo.OrderLog;

public interface OrderLogMapper {

	@Insert("insert into order_log(order_id, student_id, seat_id, order_time) "
			+ "values(#{orderLog.orderId}, #{orderLog.studentId}, #{orderLog.seatId}, #{orderLog.orderTime})")
	public void addLog(@Param("orderLog")OrderLog orderLog);

}
