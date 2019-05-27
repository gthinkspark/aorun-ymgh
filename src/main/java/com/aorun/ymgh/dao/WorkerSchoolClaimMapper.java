package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerSchoolClaim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerSchoolClaimMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerSchoolClaim record);

    int insertSelective(WorkerSchoolClaim record);

    WorkerSchoolClaim selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerSchoolClaim record);

    int updateByPrimaryKey(WorkerSchoolClaim record);

    List<WorkerSchoolClaim> getWorkerSchoolClaimListByWorkerId(@Param("workerId") Long workerId, @Param("start") Integer start, @Param("limit") Integer limit);

    void updateIsReadedStatus(Long id);

    List<WorkerSchoolClaim> getUnReadList(@Param("workerId") Long workerId);

}