package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.Message;
import com.aorun.ymgh.model.MessageReade;

import java.util.List;
import java.util.Map;

public interface MessageReadeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageReade record);

    int insertSelective(MessageReade record);

    MessageReade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageReade record);

    int updateByPrimaryKey(MessageReade record);

    List<MessageReade> selectSelective(Map<String, Object> map);

    int getTotal(Map<String, Object> map);
}