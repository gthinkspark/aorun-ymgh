package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerApplyClaim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerApplyClaimMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerApplyClaim record);

    int insertSelective(WorkerApplyClaim record);

    WorkerApplyClaim selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerApplyClaim record);

    int updateByPrimaryKey(WorkerApplyClaim record);

    List<WorkerApplyClaim> getWorkerApplyClaimListByWorkerId(@Param("workerId") Long workerId, @Param("start") Integer start, @Param("limit") Integer limit);

    List<WorkerApplyClaim> getAllList();

}