package com.whzw.yz.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whzw.yz.pojo.Seat;
import com.whzw.yz.pojo.SeatOrder;

/**
 * @author WuBN
 * @author zzy
 *
 */
@Mapper
public interface SeatOrderMapper {

	@Select("select order_code from seat_order where order_code = #{orderCode}")
	public String getIrderCode(String orderCode);

	@Insert("insert into seat_order(student_id, seat_id, order_time, date, time_quantum, order_code) "
			+ "values(#{so.studentId}, #{so.seatId}, #{so.orderTime},"
			+ " #{so.date}, #{so.timeQuantum}, #{so.orderCode})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void addOrder(@Param("so")SeatOrder seatOrder);
	
	@Delete("delete from seat_order where id = #{id}")
	public void deleteOrder(String id);

	@Select("select * from `seat_order` where date=#{date} and seat_id=#{sid} and time_quantum=#{tq}")
	public Seat findOne(@Param("sid") String seatId, @Param("date") Date date, @Param("tq") char timeQuantum);
}

