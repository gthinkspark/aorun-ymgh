package com.aorun.ymgh.base;

import java.util.List;
import java.util.Map;


/**
 * @author 作者 :LG
 * @version 创建时间：2016年8月19日 上午11:42:06
 * 类说明:
 */
public interface BaseMapper<T> {
	
 	int deleteByPrimaryKey(Long id);

    int insertSelective(T entity);

    int updateByPrimaryKeySelective(T entity);
    
    int getTotal(Map<String, Object> map);
    
    List<T> selectByMap(Map<String, Object> map);
    
    T selectByPrimaryKey(Long id);
}
