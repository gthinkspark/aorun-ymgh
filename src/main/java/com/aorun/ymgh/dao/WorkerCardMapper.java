package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerCard;
import com.aorun.ymgh.model.WorkerCardWithBLOBs;

public interface WorkerCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerCardWithBLOBs record);

    int insertSelective(WorkerCardWithBLOBs record);

    WorkerCardWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerCardWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WorkerCardWithBLOBs record);

    int updateByPrimaryKey(WorkerCard record);
}