package com.whzw.yz.util;

import java.util.UUID;

/**
 * 通用唯一识别码工具类
 * 
 * @author zzy
 * @author WuBN
 */
public class UUIDUtil {

	/**
	 * 获取32位通用唯一识别码
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
