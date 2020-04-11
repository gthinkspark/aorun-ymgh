package com.aorun.ymgh.base;


/**
 * @author 作者 :LG
 * @version 创建时间：2017年7月6日 下午3:28:57
 * 类说明:
 */
public interface BasePageService<T> extends BaseService<T>{
	public BasePagination<T> findByPage(BasePagination<T> page);
}
