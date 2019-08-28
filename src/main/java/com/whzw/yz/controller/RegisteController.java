package com.whzw.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.Result;
import com.whzw.yz.service.RegisteService;
import com.whzw.yz.vo.RegisteVo;

/**
 * 注册
 * @author wubn
 *
 */
@Controller
public class RegisteController {

	@Autowired
	RegisteService registeService;
	
	@PostMapping("/do_registe")
	@ResponseBody
	public Result<Boolean> do_Registe(@RequestBody RegisteVo registeVo){
		System.out.println(registeVo);
		Boolean is_Registe = registeService.do_Registe(registeVo);
		return Result.success(is_Registe);
	}
}
