package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerMedicalClaim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerMedicalClaimMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerMedicalClaim record);

    int insertSelective(WorkerMedicalClaim record);

    WorkerMedicalClaim selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerMedicalClaim record);

    int updateByPrimaryKey(WorkerMedicalClaim record);

    List<WorkerMedicalClaim> getWorkerMedicalClaimListByWorkerId(@Param("workerId") Long workerId, @Param("start") Integer start, @Param("limit") Integer limit);

    void updateIsReadedStatus(Long id);

    List<WorkerMedicalClaim> getUnReadList(@Param("workerId") Long workerId);

    List<WorkerMedicalClaim> getAllList();
}