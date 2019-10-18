package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerApplyClaimMapper;
import com.aorun.ymgh.model.WorkerApplyClaim;
import com.aorun.ymgh.service.WorkerApplyClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请理赔-业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerApplyClaimServiceImpl implements WorkerApplyClaimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerApplyClaimServiceImpl.class);

    @Autowired
    private WorkerApplyClaimMapper workerApplyClaimMapper;

    @Override
    public WorkerApplyClaim findWorkerApplyClaimById(Long id) {
        return workerApplyClaimMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerApplyClaim(WorkerApplyClaim workerApplyClaim) {
        return workerApplyClaimMapper.insert(workerApplyClaim);
    }

    @Override
    public int updateWorkerApplyClaim(WorkerApplyClaim workerApplyClaim) {
        return workerApplyClaimMapper.updateByPrimaryKeySelective(workerApplyClaim);
    }

    @Override
    public int deleteWorkerApplyClaim(Long id) {
        return workerApplyClaimMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerApplyClaim> getWorkerApplyClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerApplyClaimMapper.getWorkerApplyClaimListByWorkerId(workerId, start, limit);
    }

    @Override
    public List<WorkerApplyClaim> getAllList() {
        return workerApplyClaimMapper.getAllList();
    }

}
