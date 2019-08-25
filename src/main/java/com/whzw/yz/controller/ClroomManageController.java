package com.whzw.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.pojo.Clroom;
import com.whzw.yz.result.CodeMsg;
import com.whzw.yz.result.Result;
import com.whzw.yz.service.ClroomService;

/**
 * 教室管理控制器
 * 
 * @author zzy
 *
 */
@Controller
@RequestMapping("/manage/clroom")
public class ClroomManageController {

	@Autowired
	private ClroomService clroomService;

	/**
	 * 添加一间教室
	 * 
	 * @param clroom
	 * @return 添加是否成功
	 */
	@PostMapping("/add")
	@ResponseBody
	public Result<Object> addOneClroom(@RequestBody Clroom clroom) {
		boolean isSucceed = clroomService.addOneClass(clroom);
		if (isSucceed)
			return Result.success(isSucceed);
		else
			return Result.error(CodeMsg.SERVER_ERROR);
	}
}
