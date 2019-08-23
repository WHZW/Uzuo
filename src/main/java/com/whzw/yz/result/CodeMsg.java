package com.whzw.yz.result;

/**
 * 	返回信息的代码及信息内容
 * @author WuBN
 *
 */
public class CodeMsg {
	
	private int code;
	private String msg;
	
	//通用异常
	public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static final CodeMsg SERVER_ERROR = new CodeMsg(5001, "服务端异常");
	//登录模块5002XX
	public static final CodeMsg SESSION_ERROR = new CodeMsg(5002, "Session失效或不存在");
	public static final CodeMsg MOBILE_NOT_EXIST = new CodeMsg(5003, "用户手机号不存在");
	public static final CodeMsg PASSWORD_ERROR = new CodeMsg(5004, "用户密码错误");
	
	//预约模块
	public static final CodeMsg SEAT_ALREDY_ORDERED = new CodeMsg(6001, "该座位已被预约");
	
	
	
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	
	

}
