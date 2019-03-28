package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerApplyClaim;

public interface WorkerApplyClaimMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerApplyClaim record);

    int insertSelective(WorkerApplyClaim record);

    WorkerApplyClaim selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerApplyClaim record);

    int updateByPrimaryKey(WorkerApplyClaim record);
}