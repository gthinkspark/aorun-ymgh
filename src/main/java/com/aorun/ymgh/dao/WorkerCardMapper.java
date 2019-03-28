package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerCard;

public interface WorkerCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerCard record);

    int insertSelective(WorkerCard record);

    WorkerCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerCard record);

    int updateByPrimaryKeyWithBLOBs(WorkerCard record);

    int updateByPrimaryKey(WorkerCard record);
}