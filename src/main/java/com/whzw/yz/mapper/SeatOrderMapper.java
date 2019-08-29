package com.whzw.yz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	@Delete("delete from seat_order where order_id = #{id}")
	public void deleteOrder(String id);

	@Select("select * from `seat_order` where `order_code`=#{oc}")
	public SeatOrder findOneByCode(@Param("oc") String orderCode);

	@Select("select * from `seat_order` where `student_id`=#{sid} and `order_code`=#{oc}")
	public SeatOrder findOneByCodeStudentId(@Param("sid") String studentId, @Param("oc") String orderCode);

	@Select("select * from `seat_order` where `order_code` like #{ocp}")
	public List<SeatOrder> findManyByCode(@Param("ocp") String orderCodePart);

	@Select("select * from `seat_order` where order_id=#{oid}")
	public SeatOrder findOneById(@Param("oid") String orderId);
	
	@Select("select order_id, st.name as 'sn', cl.name as 'cn', s.table_id, so.seat_id, order_time, `date`, time_quantum, s.desc, is_signin " + 
			"from seat_order so, seat s, `table` t, student st, clroom cl " + 
			"where so.seat_id = s.seat_id and s.table_id = t.table_id and t.clroom_id = cl.clroom_id and so.student_id = st.student_id "
			+ "and so.student_id = #{studentId}")
	@Results({
		@Result(column = "sn",property = "studentName"),
		@Result(column = "cn",property = "clroomName"),
	})
	public List<SeatOrderVo> getOrderInfoByStudentId(String studentId);

	@Select("select order_id from seat_order where order_id = #{orderId}")
	public String getOrderIdByOrderId(String orderId);

	@Update("update `seat_order` set `is_leave`==#{i} where `order_id`=#{oid}")
	public void setIsLeave(@Param("il") int isLeave, @Param("oid") String orderId);

	@Update("update `seat_order` set `is_signin`=1 where `order_id`=#{oid}")
	public void signIn(@Param("oid") String orderId);
	
	@Select("select `is_signin` from `seat_order` where `order_id`=#{oid}")
	public int getisSignIn(@Param("oid") String orderId);

	@Select("select order_id from seat_order where student_id = #{s} and order_code like #{o}")
	public String getOrderIdByOrderCode(@Param("s")String studentId, @Param("o")String orderCodePart);
}
