package com.whzw.yz.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whzw.yz.pojo.OrderLog;

public interface OrderLogMapper {

	@Insert("insert into order_log(order_id, student_id, seat_id, order_time) "
			+ "values(#{orderLog.orderId}, #{orderLog.studentId}, #{orderLog.seatId}, #{orderLog.orderTime})")
	public void addLog(@Param("orderLog") OrderLog orderLog);

	@Update("update order_log set status = #{status} where id = #{id}")
	public void updateStatus(@Param("id") String id, @Param("status") String status);

	@Update("update `order_log` set `start_time`=#{start} where `order_id`=#{oid}")
	public void updateStartTime(@Param("oid") String orderId, @Param("start") String startTime);

	@Update("update `order_log` set `end_time`=#{end},`last`=#{last},`status`=#{status} where `order_id`=#{oid}")
	public void updateEndtimeStatusLast(@Param("oid") String orderId, @Param("end") String endTime,@Param("last") int last,@Param("status") String status);

	@Select("select `start_time` from `order_log` where order_id=#{oid}")
	public Date getStartTime(@Param("oid") String orderId);

}
