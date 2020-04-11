package com.aorun.ymgh.base;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
* @author 作者 :LG
* @version 创建时间：2017年3月9日 上午11:00:11
* 类说明:
 * @param <T>
*/
@SuppressWarnings("restriction")
public abstract class BaseServiceImpl<E extends BaseMapper<T>, T> implements BaseService<T> {
	
	protected E mapper;
	
	/**
	 * init super.mapper   eg: 	super.mapper=pageConfigMapper;
	 * @PostConstruct	aotu initMapper when bean inited
	 */
	@PostConstruct
	protected abstract void initMapper();
	
	public E getMapper(){
		return mapper;
	}
	
	public void add(T entity) {
		mapper.insertSelective(entity);
	}

	public void deleteById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void updateById(T entity) {
		mapper.updateByPrimaryKeySelective(entity);
	}

	public T findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	protected List<T> findByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

}
