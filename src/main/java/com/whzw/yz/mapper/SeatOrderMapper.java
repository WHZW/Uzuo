package com.whzw.yz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whzw.yz.pojo.SeatOrder;
import com.whzw.yz.vo.SeatOrderVo;

/**
 * @author WuBN
 * @author zzy
 *
 */
@Mapper
public interface SeatOrderMapper {

	@Select("select order_code from seat_order where order_code = #{orderCode}")
	public String getOrderCode(String orderCode);

	@Insert("insert into seat_order(student_id, seat_id, order_time, date, time_quantum, order_code) "
			+ "values(#{so.studentId}, #{so.seatId}, #{so.orderTime},"
			+ " #{so.date}, #{so.timeQuantum}, #{so.orderCode})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void addOrder(@Param("so") SeatOrder seatOrder);

	@Delete("delete from seat_order where id = #{id}")
	public void deleteOrder(String id);

	@Select("select * from `seat_order` where `order_code`=#{oc}")
	public SeatOrder findOneByCode(@Param("oc") String orderCode);

	@Select("select * from `seat_order` where `student_id`=#{sid} and `order_code`=#{oc}")
	public SeatOrder findOneByCodeStudentId(@Param("sid") String studentId, @Param("oc") String orderCode);

	@Select("selct * from `seat_order` where `order_code` like #{ocp}")
	public List<SeatOrder> findManyByCode(@Param("ocp") String orderCodePart);

	@Select("select * from `seat_order` where `id`=#{oid}")
	public SeatOrder findOneById(@Param("oid") String orderId);
	
	@Select("select order_id, student_id, seat_id, order_time, date, time_quantum from seat_order "
			+ "where student_id = #{studentId}")
	public List<SeatOrderVo> getOrderInfoByStudentId(String studentId);

	@Select("select order_id from seat_order where order_id = #{orderId}")
	public String getOrderIdByOrderId(String orderId);
}
