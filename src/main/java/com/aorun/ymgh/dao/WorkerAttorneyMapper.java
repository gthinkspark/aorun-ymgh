package com.aorun.ymgh.dao;

import com.aorun.ymgh.model.WorkerAttorney;

public interface WorkerAttorneyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerAttorney record);

    int insertSelective(WorkerAttorney record);

    WorkerAttorney selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerAttorney record);

    int updateByPrimaryKey(WorkerAttorney record);
}