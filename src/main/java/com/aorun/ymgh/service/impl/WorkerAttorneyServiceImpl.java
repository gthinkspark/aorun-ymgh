package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerAttorneyMapper;
import com.aorun.ymgh.model.WorkerAttorney;
import com.aorun.ymgh.service.WorkerAttorneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerAttorneyServiceImpl implements WorkerAttorneyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerAttorneyServiceImpl.class);

    @Autowired
    private WorkerAttorneyMapper workerAttorneyMapper;

    @Override
    public WorkerAttorney findWorkerAttorneyById(Long id) {
        return workerAttorneyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerAttorney(WorkerAttorney workerAttorney) {
        return workerAttorneyMapper.insert(workerAttorney);
    }

    @Override
    public int updateWorkerAttorney(WorkerAttorney workerAttorney) {
        return workerAttorneyMapper.updateByPrimaryKeySelective(workerAttorney);
    }

    @Override
    public int deleteWorkerAttorney(Long id) {
        return workerAttorneyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerAttorney> getWorkerAttorneyListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        //return workerAttorneyMapper.getWorkerAttorneyListByWorkerId(workerId,start,limit);
        return null;
    }

}
