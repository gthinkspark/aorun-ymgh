package com.aorun.ymgh.service;

import com.aorun.ymgh.model.Message;
import com.aorun.ymgh.model.MessageReade;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

/**
 * @author 作者 :LG
 * @version 创建时间：2016年12月7日 下午2:28:10
 * 类说明:
 */
public interface MessageReadeService {
    public List<MessageReade> findByMap(Map<String, Object> map);

    public void add(MessageReade messageReade) throws DataAccessException;

    public void deleteById(Long id) throws DataAccessException;

    public void update(MessageReade messageReade) throws DataAccessException;

    public MessageReade findById(Long id) throws DataAccessException;

    public int getTotalByMap(Map<String, Object> map);
}
