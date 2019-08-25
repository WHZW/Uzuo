package com.whzw.yz.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whzw.yz.result.CodeMsg;
import com.whzw.yz.result.Result;

/**
 * 
 * @author zzy
 * @author WuBN
 */
@Controller
@ResponseBody
public class GlobalExceptionHandler {

	public Result<String> exceptionHandler(Exception e) {
		if (e instanceof GlobalException) {
			GlobalException globalException = (GlobalException) e;
			return Result.error(globalException.getCodeMsg());
		} else {
			e.printStackTrace();
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}
}
