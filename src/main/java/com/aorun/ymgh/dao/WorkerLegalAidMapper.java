package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerLegalAid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerLegalAidMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerLegalAid record);

    int insertSelective(WorkerLegalAid record);

    WorkerLegalAid selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerLegalAid record);

    int updateByPrimaryKey(WorkerLegalAid record);

    List<WorkerLegalAid> getWorkerLegelAidListByWorkerId(@Param("workerId") Long workerId, @Param("start") Integer start, @Param("limit")Integer limit);
}