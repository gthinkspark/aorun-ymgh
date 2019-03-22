package com.aorun.ymgh.util;


import org.springframework.util.ObjectUtils;

/**
 * @description 检查Object类型数据是否为null
 * @author 索亮
 * @date 2015年7月31日上午10:12:04
 */
public class CheckObjectIsNull {
	/**
	 * 检查传入的Object类型数据是否为null
	 * @param objects
	 * @return
	 */
	public static boolean isNull(Object...objects){
		for (int i = 0; i < objects.length; i++) {
			if (ObjectUtils.isEmpty(objects[i])) {
				return true;
			}
		}
		return false;
	}
	
}
