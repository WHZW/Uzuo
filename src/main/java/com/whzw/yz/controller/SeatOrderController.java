package com.whzw.yz.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.Result;
import com.whzw.yz.service.SeatOrderService;
import com.whzw.yz.vo.OrderVo;
import com.whzw.yz.vo.SeatOrderVo;

/**
 * 座位预约
 * @author wubn
 *
 */
@Controller
@RequestMapping("/seatOrder")
public class SeatOrderController {
	
	@Autowired
	SeatOrderService seatOrderService;
	
	@PostMapping("/order")
	@ResponseBody
	public Result<SeatOrderVo> order(@RequestBody OrderVo orderVo, HttpServletRequest request){
		System.out.println(orderVo);
		SeatOrderVo seatOrderVo = seatOrderService.order(orderVo, request);
		return Result.success(seatOrderVo);
	}
	
	@PostMapping("/cencle")
	@ResponseBody
	public Result<Boolean> cencle(@RequestBody Map<String, String> map){
		seatOrderService.cancelOrder(map.get("orderId"));
		return Result.success(true);
	}

}
