package com.aorun.ymgh.util.cache.redis;


import org.springframework.util.StringUtils;

/**
 * 
 * @author Leon
 *
 */
public class CheckIsEmpty {
	/**
	 * 检查传入的String类型数据是否有空值
	 * @param a
	 * @param strings
	 * @return
	 */
	public static boolean isEmpty(String a,String...strings){
		if (StringUtils.isEmpty(a)) {
			return true;
		}	
		for (int i = 0; i < strings.length; i++) {
			if (StringUtils.isEmpty(strings[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查出入的String类型数据是否全部为空值
	 * @param a
	 * @param strings
	 * @return
	 */
	public static boolean isNotEmpty(String a,String...strings){
		if (StringUtils.isEmpty(a)) {
			return true;
		}	
		for (int i = 0; i < strings.length; i++) {
			if (StringUtils.isEmpty(strings[i])) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
