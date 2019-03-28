package com.aorun.ymgh.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.aorun.ymgh.dao.MessageMapper;
import com.aorun.ymgh.model.Message;
import com.aorun.ymgh.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


/**
 * @author 作者 :LG
 * @version 创建时间：2016年8月4日 下午3:53:17
 * 类说明:
 */
@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageMapper messageMapper;

	public void add(Message entity) throws DataAccessException {
		messageMapper.insertSelective(entity);
	}

	public void deleteById(Long id) throws DataAccessException {
		messageMapper.deleteByPrimaryKey(id);
	}

	public void update(Message entity) throws DataAccessException {
		messageMapper.updateByPrimaryKeySelective(entity);
	}

	public Message findById(Long id) throws DataAccessException {
		return messageMapper.selectByPrimaryKey(id);
	}

	public List<Message> findByMap(Map<String, Object> map) {
		return messageMapper.selectSelective(map);
	}

	public int getTotalByMap(Map<String, Object> map) {
		return messageMapper.getTotal(map);
	}

}
