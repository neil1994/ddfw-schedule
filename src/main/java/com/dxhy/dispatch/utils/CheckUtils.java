package com.dxhy.dispatch.utils;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dxhy.dispatch.manage.constants.RegexEnum;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 校验工具类
 * </p>
 *
 * @author 赵睿
 * @version 1.0 Created on 2016年11月1日 上午10:08:23
 */
public class CheckUtils {
	
	/** 邮件校验正则 表达式*/
	public final static String emailCheckRegex="^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)"
			+ "+[a-zA-Z]{2,}$";
	
	/** url校验正则表达式 */
	public final static String urlCheckRegex="^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)"
			+ "(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*"
			+ "(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";



	/**
	 * <p>
	 * 功能实现描述
	 * </p>
	 * 
	 * @param param
	 *            需要校验的参数
	 * @param notNull
	 *            true为需要进行非空校验
	 * @param digit
	 *            数据长度校验，0或者负数为不需要进行长度校验
	 * @param describe
	 *            数据描述
	 * @throws IOException
	 * @author 赵睿 2016年11月1日上午10:07:33
	 */
	public static void paramcheck(String param, boolean notNull, int digit, String describe) throws IOException {
		if (notNull) {
			if (org.apache.commons.lang.StringUtils.isBlank(param)) {
				throw new IOException(describe + "--->非空校验失败！");
			}
		} else {
			if (!StringUtils.isBlank(param)) {
				if (digit > 0) {
					if (param.length() > digit) {
						String[] strings = { describe, "--->长度校验失败，标准长度为", String.valueOf(digit), "实际长度为",
								String.valueOf(param.length()) };
						throw new IOException(StringUtils.join(strings));
					}
				}
			}
		}
	}

	/**
	 * 对数据进行正则匹配
	 * @param param 参数
	 * @param notNull 是否为空
	 * @param digit 几位数
	 * @param describe 描述信息
	 * @param regexEnum 正则表达式
     * @throws IOException
     */
	public static void paramcheck(String param, boolean notNull, int digit, String describe, RegexEnum regexEnum) throws IOException{
		paramcheck(param, true,digit,describe);
		if(!regxCheck(param,regexEnum.getRegex())){
			String[] strings = { describe, "--->正则校验失败，请求参数为",param ,"校验规则为",regexEnum.getRegexDes()};
			throw new IOException(StringUtils.join(strings));
		}

	}

	public static void paramcheck(String param, boolean notNull, int digit, String describe, RegexEnum[] regexEnums) throws IOException{
		paramcheck(param,notNull,digit,describe);

		boolean flag=false;
		for (RegexEnum regexEnum : regexEnums) {
			flag = flag || regxCheck(param, regexEnum.getRegex());
		}
		if(!flag){
			String[] strings = { describe, "--->正则校验失败，请求参数为",param};
			throw new IOException(StringUtils.join(strings));
		}

	}

	public static void paramcheck(String[] params, boolean notNull, int digit, String describe)
			throws IOException {
		if (params == null)
			return;
		for (String param : params) {
			paramcheck(param, notNull, digit, describe);
		}
	}

	/**
	 * 
	 * <p>
	 * 参数校验，可以空串，但是不能为null
	 * </p>
	 * 
	 * @author 赵睿 2016年11月7日上午9:13:49
	 */
	public static void paramcheck_CouldEmptyString_NotNull(String[] params, String describe) {
		for (String param : params) {
			if (param == null) {
				throw new RuntimeException(describe + "--->为null，而不是“”");
			}
		}
	}
	
	
	/**
	 * <p>
	 * 对邮件格式进行校验
	 * </p>
	 * 
	 * @param email 邮件
	 * @return boolean
	 * @author 赵睿 2016年11月7日下午2:02:42
	 */
	public static boolean checkEmail(String email) {
		return regxCheck(email, emailCheckRegex);
	}
	
	/**
	 * <p>
	 * 对邮件格式进行校验
	 * </p>
	 * 
	 * @param url 邮件地址
	 * @return boolean
	 * @author 赵睿 2016年11月7日下午2:02:42
	 */
	public static boolean checkUrl(String url) {
		return regxCheck(url, urlCheckRegex);
	}
	
	/**
	 * <p>正则校验</p>
	 * 
	 * @param str 请求参数
	 * @param check 正则校验
	 * @return boolean
	 * @author 赵睿  2016年11月8日上午9:46:37
	 */
	public static boolean regxCheck(String str,String check){
		boolean flag;
		try {
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
}
