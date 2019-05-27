package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerMedicalClaimMapper;
import com.aorun.ymgh.model.WorkerMedicalClaim;
import com.aorun.ymgh.service.WorkerMedicalClaimService;
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
public class WorkerMedicalClaimServiceImpl implements WorkerMedicalClaimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerMedicalClaimServiceImpl.class);

    @Autowired
    private WorkerMedicalClaimMapper workerMedicalClaimMapper;

    @Override
    public WorkerMedicalClaim findWorkerMedicalClaimById(Long id) {
        return workerMedicalClaimMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerMedicalClaim(WorkerMedicalClaim workerMedicalClaim) {
        return workerMedicalClaimMapper.insert(workerMedicalClaim);
    }

    @Override
    public int updateWorkerMedicalClaim(WorkerMedicalClaim workerMedicalClaim) {
        return workerMedicalClaimMapper.updateByPrimaryKeySelective(workerMedicalClaim);
    }

    @Override
    public int deleteWorkerMedicalClaim(Long id) {
        return workerMedicalClaimMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerMedicalClaim> getWorkerMedicalClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {

        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerMedicalClaimMapper.getWorkerMedicalClaimListByWorkerId(workerId, start, limit);
    }

    @Override
    public void updateIsReadedStatus(Long id) {
        workerMedicalClaimMapper.updateIsReadedStatus(id);
    }

    @Override
    public List<WorkerMedicalClaim> getUnReadList(Long workerId) {
        return workerMedicalClaimMapper.getUnReadList(workerId);
    }

}
