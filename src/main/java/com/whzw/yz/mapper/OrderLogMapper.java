package com.whzw.yz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whzw.yz.pojo.OrderLog;
import com.whzw.yz.vo.OrderLogVo;

/**
 * 预约日志
 * @author wubn
 *
 */
public interface OrderLogMapper {

	@Insert("insert into order_log(order_id, student_id, seat_id, order_time, end_time) "
			+ "values(#{orderLog.orderId}, #{orderLog.studentId}, #{orderLog.seatId}, #{orderLog.orderTime}, "
			+ "#{orderLog.endTime})")
	public void addLog(@Param("orderLog")OrderLog orderLog);

	@Update("update order_log set status = #{status} where id = #{id}")
	public void updateStatus(@Param("id")String id, @Param("status")String status);
	
	@Select("select order_id, student_id, seat_id, order_time, start_time, end_time, last, status "
			+ "from order_log "
			+ "where student_id = #{studetnId} and status <> '未完成'")
	public List<OrderLogVo> getHistory(String studentId);

}
