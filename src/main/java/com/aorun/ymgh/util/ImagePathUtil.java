/**
 * 
 */
package com.aorun.ymgh.util;

import com.aorun.ymgh.util.biz.ImagePropertiesConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

/**
 * @author mengshaobo
 *
 */
public class ImagePathUtil {
	
	private static final Log LOGGER = LogFactory.getLog(ImagePathUtil.class);
	/**
	 * 获取文件系统的相对路径
	 * @param fileJavaPath 文件java地址
	 * @return
	 */
	public static String getFileName(String fileJavaPath){
		String fileName = "";
		if(StringUtils.isEmpty(fileJavaPath)){
			fileName =  fileJavaPath.substring(fileJavaPath.indexOf("userfiles/")+"userfiles/".length());
		}
		return fileName;
	}

	/**
	 * 获取多个图片名称
	 * @param fileJavaPaths 图片java地址
	 * @return
	 */
	
	public static String getFileStringName(String fileJavaPaths){
		if(null==fileJavaPaths||StringUtils.isEmpty(fileJavaPaths)){
			return "";
		}
		StringBuilder stringBuffer = new StringBuilder();
		fileJavaPaths.replace("|", ",");
		String[] gd =StringUtils.split(fileJavaPaths, ",");
		for(int i = 0 ; i < gd.length;i++){
			stringBuffer.append(getFileName(gd[i])+",");
		}
		if(stringBuffer.toString().endsWith(",")){
			stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
		}
		return stringBuffer.toString();
	}

	/**
	 * 给图片加域名
	 * 
	 * @param pathUrl
	 */
	public static String setFileServerName(String pathUrl) {
		if (!StringUtils.isEmpty(pathUrl) && !pathUrl.startsWith("http")) {
			return ImagePropertiesConfig.CDN_SERVER_ROOT_PATH + pathUrl;
		}
		return pathUrl;
	}
	/**
	 * 给多图片加域名
	 * 
	 * @param pathPathString
	 */
	public static String setFilePathStringServerName(String pathPathString) {
		if(null==pathPathString||StringUtils.isEmpty(pathPathString)){
			return "";
		}
		String[] split = pathPathString.split(",");
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0;i<split.length;i++){
			stringBuffer.append(setFileServerName(split[i])+",");
		}
		if(stringBuffer.toString().endsWith(",")){
			stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
		}
		return stringBuffer.toString();
	}
	

	

}
