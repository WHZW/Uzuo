package com.whzw.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void showClroomSeats() {

	}

	@GetMapping("/time")
	public void showSeatsByTime() {

	}

}
