package com.aorun.ymgh.service;

import java.util.List;
import java.util.Map;

import com.aorun.ymgh.model.Message;
import org.springframework.dao.DataAccessException;

/**
 * @author 作者 :LG
 * @version 创建时间：2016年8月4日 下午3:50:10
 * 类说明:
 */
public interface MessageService{
    public List<Message> findByMap(Map<String, Object> map);

    public void add(Message message) throws DataAccessException;

    public void deleteById(Long id) throws DataAccessException;

    public void update(Message message) throws DataAccessException;

    public Message findById(Long id) throws DataAccessException;

    public int getTotalByMap(Map<String, Object> map);
}
