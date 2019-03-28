package com.aorun.ymgh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


/**
 * @author 作者 :LG
 * @version 创建时间：2016年12月7日 下午3:05:29
 * 类说明:
 */
@Service("messageReadeService")
public class MessageReadeServiceImpl implements MessageReadeService {
	@Autowired
	private MessageReadeMapper messageReadeMapper;

	public void add(MessageReade entity) throws DataAccessException {
		messageReadeMapper.insertSelective(entity);
	}

	public void deleteById(Long id) throws DataAccessException {
		messageReadeMapper.deleteByPrimaryKey(id);
	}

	public void update(MessageReade entity) throws DataAccessException {
		messageReadeMapper.updateByPrimaryKeySelective(entity);
	}

	public MessageReade findById(Long id) throws DataAccessException {
		return messageReadeMapper.selectByPrimaryKey(id);
	}

}
