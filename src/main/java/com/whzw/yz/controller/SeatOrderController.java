package com.whzw.yz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.Result;
import com.whzw.yz.service.SeatOrderService;
import com.whzw.yz.vo.OrderVo;

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
	
	@PostMapping("/orderSeat")
	@ResponseBody
	public Result<Boolean> order(@RequestBody OrderVo orderVo, HttpServletRequest request){
		System.out.println(orderVo);
		boolean success = seatOrderService.order(orderVo, request);
		return Result.success(success);
	}

}
