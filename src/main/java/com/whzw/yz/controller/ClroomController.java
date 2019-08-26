package com.whzw.yz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.Result;
import com.whzw.yz.service.SeatShowService;
import com.whzw.yz.vo.seatshow.ClroomInfoVo;
import com.whzw.yz.vo.seatshow.DateTimeClroomIdVo;

@Controller()
@RequestMapping("/library/clroom/show")
public class ClroomController {

	@Autowired
	private SeatShowService seatShowService;

	@GetMapping("/all")
	@ResponseBody
	public Result<List<ClroomInfoVo>> showClrooms(@RequestBody DateTimeClroomIdVo dateTimeClroomIdVo) {
		List<ClroomInfoVo> clroomInfoVos = seatShowService.getAllClroomInfo(dateTimeClroomIdVo);
		return Result.success(clroomInfoVos);
	}
}
