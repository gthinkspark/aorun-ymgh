package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerTempClaimMapper;
import com.aorun.ymgh.model.WorkerTempClaim;
import com.aorun.ymgh.service.WorkerTempClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 医疗救助业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerTempClaimServiceImpl implements WorkerTempClaimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerTempClaimServiceImpl.class);

    @Autowired
    private WorkerTempClaimMapper workerTempClaimMapper;

    @Override
    public WorkerTempClaim findWorkerTempClaimById(Long id) {
        return workerTempClaimMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerTempClaim(WorkerTempClaim workerTempClaim) {
        return workerTempClaimMapper.insert(workerTempClaim);
    }

    @Override
    public int updateWorkerTempClaim(WorkerTempClaim workerTempClaim) {
        return workerTempClaimMapper.updateByPrimaryKeySelective(workerTempClaim);
    }

    @Override
    public int deleteWorkerTempClaim(Long id) {
        return workerTempClaimMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerTempClaim> getWorkerTempClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerTempClaimMapper.getWorkerTempClaimListByWorkerId(workerId,start,limit);
    }




}
