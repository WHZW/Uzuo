package com.whzw.yz.util;

import java.util.Calendar;
import java.util.Date;

import com.whzw.yz.vo.TimeVo;

public class TimeUtil {
	
	public static TimeVo getTimeVo(Date date) {
		TimeVo timeVo = new TimeVo();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		timeVo.setYear(calendar.get(Calendar.YEAR));
		timeVo.setMonth(calendar.get(Calendar.MONTH) + 1);
		timeVo.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		timeVo.setHour(hour);
		if (hour >= 0 && hour < 12) {
			timeVo.setTimeQuantum('M');
		} else if (hour >= 12 && hour < 18) {
			timeVo.setTimeQuantum('A');
		} else if (hour >= 18 && hour < 24) {
			timeVo.setTimeQuantum('N');
		}		
		return timeVo;
	}
	
	

}
