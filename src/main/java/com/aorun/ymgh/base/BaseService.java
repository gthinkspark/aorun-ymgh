package com.aorun.ymgh.base;


public interface BaseService<T> {
	
	public void add(T entity);
	
	public void deleteById(Long id);
	
	public void updateById(T entity);
	
	public T findById(Long id);
}