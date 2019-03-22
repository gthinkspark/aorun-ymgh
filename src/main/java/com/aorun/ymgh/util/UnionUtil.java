package com.aorun.ymgh.util;


import com.aorun.ymgh.controller.login.UserDto;

/**
 * @author 作者 LG
 * @version 
 * @data 创建时间：2019年3月19日 上午10:43:01
 * 类说明	
 */
public class UnionUtil {
	private static final String unionSidPrefix="GH_";
	public static String generateUnionSid(
			UserDto userDto){
		return unionSidPrefix+userDto.getSid();
	}
}
