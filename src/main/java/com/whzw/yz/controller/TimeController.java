package com.whzw.yz.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.Result;
import com.whzw.yz.util.TimeUtil;
import com.whzw.yz.vo.TimeVo;

@Controller
@RequestMapping("/datetime")
public class TimeController {

	@GetMapping("/now")
	@ResponseBody
	public Result<Map<String, TimeVo>> getRightTime() {
		Map<String, TimeVo> map = new HashMap<>();
		
		Date today = Calendar.getInstance().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date afterTomorrow = calendar.getTime();
		
		map.put("today", TimeUtil.getTimeVo(today));
		map.put("tomorrow", TimeUtil.getTimeVo(tomorrow));
		map.put("afterTomorrow", TimeUtil.getTimeVo(afterTomorrow));
		
		return Result.success(map);
	}

	// 签到
	// 离开座位
}
