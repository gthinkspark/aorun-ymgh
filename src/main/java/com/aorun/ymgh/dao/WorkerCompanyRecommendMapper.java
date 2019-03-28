package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerCompanyRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerCompanyRecommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerCompanyRecommend record);

    int insertSelective(WorkerCompanyRecommend record);

    WorkerCompanyRecommend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerCompanyRecommend record);

    int updateByPrimaryKey(WorkerCompanyRecommend record);

    List<WorkerCompanyRecommend> getWorkerCompanyRecommendList(@Param("start") Integer start, @Param("limit")Integer limit);

}