package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.MessageReadeMapper;
import com.aorun.ymgh.model.MessageReade;
import com.aorun.ymgh.service.MessageReadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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

    public List<MessageReade> findByMap(Map<String, Object> map) {
        return messageReadeMapper.selectSelective(map);
    }

    public int getTotalByMap(Map<String, Object> map) {
        return messageReadeMapper.getTotal(map);
    }

}
