package com.whzw.yz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.pojo.Clroom;
import com.whzw.yz.result.Result;
import com.whzw.yz.service.SeatShowService;
import com.whzw.yz.vo.seatshow.DateTimeClroomIdVo;
import com.whzw.yz.vo.seatshow.SeatStatusVo;

/**
 * 查看图书馆自习室座位状态
 * 
 * @author zzy
 *
 */
@Controller
@RequestMapping("/library/seat/show")
public class SeatShowController {

	@Autowired
	private SeatShowService seatShowService;

	/**
	 * 获取指定id的教室信息 包括（教室信息、所有桌子信息、桌子包含的所有座位信息（没有状态信息））
	 * 
	 * @param clroomId
	 * @return
	 */
	@GetMapping("/clroom")
	@ResponseBody
	public Result<Clroom> showClroomSeats(@RequestParam("clroomId") String clroomId) {
		Clroom clroom = seatShowService.getClroomInfo(clroomId);
		return Result.success(clroom);
	}

	@GetMapping("/status")
	@ResponseBody
	public Result<List<SeatStatusVo>> showSeatsByTime(@RequestBody DateTimeClroomIdVo datetimeClroomIdVo) {
		return Result.success(seatShowService.getAllSeatsSatus(datetimeClroomIdVo));

	}

}
