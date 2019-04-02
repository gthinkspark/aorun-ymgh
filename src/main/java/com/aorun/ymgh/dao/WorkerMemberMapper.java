package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerMember;

public interface WorkerMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerMember record);

    int insertSelective(WorkerMember record);

    WorkerMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerMember record);

    int updateByPrimaryKey(WorkerMember record);
}