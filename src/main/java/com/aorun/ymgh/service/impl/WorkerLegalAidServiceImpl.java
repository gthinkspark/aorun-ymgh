package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerLegalAidMapper;
import com.aorun.ymgh.model.WorkerLegalAid;
import com.aorun.ymgh.service.WorkerLegelAidService;
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
public class WorkerLegalAidServiceImpl implements WorkerLegelAidService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerLegalAidServiceImpl.class);

    @Autowired
    private WorkerLegalAidMapper workerLegalAidMapper;

    @Override
    public WorkerLegalAid findWorkerLegelAidById(Long id) {
        return workerLegalAidMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerLegelAid(WorkerLegalAid workerLegalAid) {
        return workerLegalAidMapper.insert(workerLegalAid);
    }

    @Override
    public int updateWorkerLegelAid(WorkerLegalAid workerLegalAid) {
        return workerLegalAidMapper.updateByPrimaryKeySelective(workerLegalAid);
    }

    @Override
    public int deleteWorkerLegelAid(Long id) {
        return workerLegalAidMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerLegalAid> getWorkerLegelAidListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerLegalAidMapper.getWorkerLegelAidListByWorkerId(workerId,start,limit);
    }

}
