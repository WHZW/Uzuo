package com.whzw.yz.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.Result;

@Controller
@RequestMapping("/datetime")
public class TimeController {

	@GetMapping("/now")
	@ResponseBody
	public Result<Map<String, Object>> getRightTime() {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		String time = format.format(Calendar.getInstance().getTime());
		String[] arr = time.split("-");
		map.put("year", arr[0]);
		map.put("month", arr[1].replaceFirst("^0*", ""));
		map.put("day", arr[2].replaceFirst("^0*", ""));
		int hour = Integer.parseInt(arr[3]);
		if (hour >= 0 && hour < 12) {
			map.put("timeQuantum", 'M');
		} else if (hour >= 12 && hour < 18) {
			map.put("timeQuantum", 'A');
		} else if (hour >= 18 && hour < 24) {
			map.put("timeQuantum", 'N');
		}
		return Result.success(map);
	}

	// 签到
	// 离开座位
}
