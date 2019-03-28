package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerAttorneyReplyAdvisory;

public interface WorkerAttorneyReplyAdvisoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerAttorneyReplyAdvisory record);

    int insertSelective(WorkerAttorneyReplyAdvisory record);

    WorkerAttorneyReplyAdvisory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerAttorneyReplyAdvisory record);

    int updateByPrimaryKey(WorkerAttorneyReplyAdvisory record);
}