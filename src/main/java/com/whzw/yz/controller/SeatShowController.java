package com.whzw.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.pojo.Clroom;
import com.whzw.yz.result.Result;
import com.whzw.yz.service.SeatShowService;

/**
 * 查看图书馆自习室座位状态
 * 
 * @author zzy
 *
 */
@Controller
@RequestMapping("/seat/show")
public class SeatShowController {

	@Autowired
	private SeatShowService seatShowService;

	@GetMapping("/clroom")
	@ResponseBody
	public Result<Clroom> showClroomSeats(@RequestParam("clroomId") String clroomId) {
		Clroom clroom = seatShowService.showClroomSeats(clroomId);
		return Result.success(clroom);
	}

	@GetMapping("/time")
	public void showSeatsByTime() {

	}

}
