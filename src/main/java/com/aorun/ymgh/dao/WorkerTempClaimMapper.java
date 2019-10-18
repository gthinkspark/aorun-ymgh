package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerTempClaim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerTempClaimMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerTempClaim record);

    int insertSelective(WorkerTempClaim record);

    WorkerTempClaim selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerTempClaim record);

    int updateByPrimaryKey(WorkerTempClaim record);

    List<WorkerTempClaim> getWorkerTempClaimListByWorkerId(@Param("workerId") Long workerId, @Param("start") Integer start, @Param("limit") Integer limit);

    void updateIsReadedStatus(Long id);

    List<WorkerTempClaim> getUnReadList(@Param("workerId") Long workerId);


    List<WorkerTempClaim> getAllList();
}