package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerCardApply;
import org.apache.ibatis.annotations.Param;

public interface WorkerCardApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerCardApply record);

    WorkerCardApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerCardApply record);

    int updateByPrimaryKey(WorkerCardApply record);

    WorkerCardApply findWorkerCardApplyByWorkerIdAndCardId(@Param("workerId") Long workerId, @Param("workerCardId") Long workerCardId);

    void updateIsReadedStatus(Long id);
}