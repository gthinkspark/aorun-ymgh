package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerLiveClaim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerLiveClaimMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerLiveClaim record);

    int insertSelective(WorkerLiveClaim record);

    WorkerLiveClaim selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerLiveClaim record);

    int updateByPrimaryKey(WorkerLiveClaim record);

    List<WorkerLiveClaim> getWorkerLiveClaimListByWorkerId(@Param("workerId") Long workerId, @Param("start") Integer start, @Param("limit")Integer limit);

    void updateIsReadedStatus(Long id);

    List<WorkerLiveClaim> getUnReadList(@Param("workerId") Long workerId);

}