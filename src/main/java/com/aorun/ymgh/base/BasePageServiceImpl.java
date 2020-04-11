package com.aorun.ymgh.base;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author 作者 :LG
 * @version 创建时间：2017年7月6日 下午3:31:25
 * 类说明:
 */
public abstract class BasePageServiceImpl<E extends BaseMapper<T>,T> extends BaseServiceImpl<E, T> implements
		BaseService<T> {
	
	public BasePagination<T> findByPage(BasePagination<T> page){
		if(null==mapper){
			initMapper();
		}
		Map<String,Object> params = page.getSearchParamsMap();
		page.setCurrentTotal(mapper.getTotal(params));
		if(page.getCurrentPage() > 0){
			page.setResult(mapper.selectByMap(params));
		}else{
			page.setResult(new ArrayList<T>());
		}
		return page;
	}
}
