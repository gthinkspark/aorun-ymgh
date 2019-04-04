package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerAdvisory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerAdvisoryMapper {


    int insert(WorkerAdvisory record);

    WorkerAdvisory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerAdvisory record);

    int updateByPrimaryKey(WorkerAdvisory record);

    /**
     *
     * @param workerId
     * @param start
     * @param limit
     * @return
     */
    List<WorkerAdvisory> getWorkerAdvisoryListByWorkerId(@Param("workerId") Long workerId,@Param("start") Integer start, @Param("limit")Integer limit);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);


    List<WorkerAdvisory> getUnReadWorkerAdvisoryList(@Param("workerId") Long workerId);

}