package com.dxhy.dispatch.utils;

import java.util.UUID;

/**
 *
 * String工具类
 *
 * @author 陈玉航
 * @version 1.0 Created on 2016年7月22日 下午2:56:47
 */
public class StringUtils {

	/***
	 *
	 * <p>
	 * 通过StringBuilder拼接字符串
	 * </p>
	 *
	 * @param args
	 * @return String
	 * @author 赵睿
	 * @date Created on 2016年5月30日 上午10:15:11
	 */
	public static String stringConcatenation(String... args) {

		StringBuilder builder = new StringBuilder();

		for (String string : args) {

			builder.append(string);

		}

		return builder.toString();
	}

	/**
	 *
	 * <p>
	 * 获得唯一的uuid
	 * </p>
	 *
	 * @return String
	 * @author: 赵睿
	 * @date: Created on 2016年5月27日 上午10:23:45
	 */
	public static String getUUid() {

		return UUID.randomUUID().toString().replaceAll("-", "");

	}

	/**
	 *
	 * <p>
	 * 字符串首字母大写
	 * </p>
	 *
	 * @param str
	 * @return String
	 * @author: 赵睿
	 * @date: Created on 2016年4月18日 下午2:21:10
	 */
	public static String upperFirst(String str) {

		char[] chars = str.toCharArray();

		if (Character.isLowerCase(chars[0])) {

			chars[0] -= 32;

		}

		return new String(chars);
	}

	/**
	 * <p>
	 * 根据split切割，取tag之前的元素
	 * </p>
	 * 前置条件，需要校验该输入是否存在tag
	 *
	 * @param input 输入
	 * @param tag  标签
	 * @param split 分割符
	 * @return String 响应信息
	 * @author 赵睿 2016年8月2日上午8:57:58
	 */
	public static String getBefore(String input, String tag, String split) {
		String[] strs = input.split(split);

		int i;
		for (i = 0; i < strs.length; i++) {
			if (strs[i].equalsIgnoreCase(tag))
				break;
		}
		input = strs[i - 1];
		return input;
	}

}
