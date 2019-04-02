package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerAttorneyReplyAdvisory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerAttorneyReplyAdvisoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerAttorneyReplyAdvisory record);

    int insertSelective(WorkerAttorneyReplyAdvisory record);

    WorkerAttorneyReplyAdvisory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerAttorneyReplyAdvisory record);

    int updateByPrimaryKey(WorkerAttorneyReplyAdvisory record);

    List<WorkerAttorneyReplyAdvisory> getWorkerAttorneyReplyAdvisoryListByWorkerId(@Param("workerId")Long workerId,@Param("advisoryId") Long advisoryId, @Param("requestTimePoint")String requestTimePoint);

}