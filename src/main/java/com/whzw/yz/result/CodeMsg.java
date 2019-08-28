package com.whzw.yz.result;

/**
 * 返回信息的代码及信息内容
 * 
 * @author WuBN
 *
 */
public class CodeMsg {

	private int code;

	private String msg;

	// 通用异常
	public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static final CodeMsg SERVER_ERROR = new CodeMsg(5001, "服务端异常");
	// 登录模块5002XX
	public static final CodeMsg SESSION_ERROR = new CodeMsg(5002, "Session失效或不存在");
	public static final CodeMsg STUDENT_NOT_EXIST = new CodeMsg(5003, "用户不存在");
	public static final CodeMsg PASSWORD_ERROR = new CodeMsg(5004, "用户密码错误");

	// 预约模块
	public static final CodeMsg SEAT_ALREDY_ORDERED = new CodeMsg(6001, "该座位已被预约");
	public static final CodeMsg ORDER_TIME_PASS = new CodeMsg(6002, "预约时间已过");
	public static final CodeMsg NO_VALID_ORDER = new CodeMsg(6003, "无有效预约");
	public static final CodeMsg NOT_IN_TIME = new CodeMsg(6004, "未到时间");
	public static final CodeMsg ORDER_NOT_EXIST = new CodeMsg(6005, "该预约不存在");
	public static final CodeMsg INTEGRAL_NOT_ENOUGH = new CodeMsg(6006, "信用积分不足");

	// 教室及座位信息查看相关
	public static final CodeMsg NOT_FIND_SEAT = new CodeMsg(7001, "没找到座位");

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
