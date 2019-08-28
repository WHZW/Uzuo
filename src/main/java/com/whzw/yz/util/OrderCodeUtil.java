package com.whzw.yz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.whzw.yz.pojo.OrderCode;
import com.whzw.yz.vo.OrderVo;

public class OrderCodeUtil {

	@SuppressWarnings("deprecation")
	public static String encode(OrderCode orderCode) {
		int year = Integer.parseInt(orderCode.getYear());
		int month = Integer.parseInt(orderCode.getMonth());
		int day = Integer.parseInt(orderCode.getDay());
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		return sdFormat.format(new Date(year - 1900, month - 1, day)) + orderCode.getTimeQuantum()
				+ orderCode.getSeatId();
	}

	@SuppressWarnings("deprecation")
	public static String encode(OrderVo orderVo) {
		int year = orderVo.getYear();
		int month = orderVo.getMonth();
		int day = orderVo.getDay();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		return sdFormat.format(new Date(year - 1900, month - 1, day)) + orderVo.getTimeQuantum() + orderVo.getSeatId();
	}

	public static OrderCode decode(String code) {

		OrderCode orderCode = new OrderCode();
		orderCode.setYear(code.substring(0, 4));
		orderCode.setMonth(code.substring(4, 6).replaceFirst("^0*", ""));
		orderCode.setDay(code.substring(6, 8).replaceFirst("^0*", ""));
		orderCode.setTimeQuantum(code.charAt(8));
		orderCode.setSeatId(code.substring(9));
		return orderCode;
	}
}
