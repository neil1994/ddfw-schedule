package com.dxhy.dispatch.utils;

import com.dxhy.dispatch.manage.constants.SystemConstants;
import org.apache.commons.codec.binary.Base64;

/**
 * 
 * 
 * @ClassName ：Base64Encoding
 * @Description ：base64加密工具类
 * @author ：张双超
 * @date ：2016年10月24日 下午5:06:21
 * 
 *
 */
public class Base64Encoding {

	
	/**
	 * 
	 * @Title : encode(base64加密)
	 * @Description ：BASE64加密
	 * @param @param res
	 * @param @return 
	 * @return byte[]
	 * @exception 
	 *
	 */
	public static byte[] encode(byte[] res) {
		return Base64.encodeBase64(res);
	}
	/**
	 * BASE64加密
	 * byte==>String
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encodeToString(byte[] key) throws Exception {
		return new String(encode(key), SystemConstants.charset);
	}
	
	/**
	 * BASE64加密
	 * String==>String
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encode(String key) throws Exception {
		return new String(encode(key.getBytes(SystemConstants.charset)));
	}
	
	/**
	 * 
	 * @Title : decode(base64解密)
	 * @Description ：base64解密
	 * @param @param str
	 * @param @return 
	 * @return byte[]
	 * @exception 
	 *
	 */
	public static byte[] decode(byte[] key) {
		return Base64.decodeBase64(key);
	}
	/**
	 * BASE64解密
	 * String==>byte
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(String key) throws Exception {
		return decode(key.getBytes(SystemConstants.charset));
	}
	
	/**
	 * BASE64解密
	 * String==>byte
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decodeToString(String key) throws Exception {
		return new String(decode(key.getBytes(SystemConstants.charset)),SystemConstants.charset);
	}
	
	public static String encodeUrl(byte[] key){
		byte[] encodeBase64URLSafe = Base64.encodeBase64URLSafe(key);
		return new String(encodeBase64URLSafe);
	}
}