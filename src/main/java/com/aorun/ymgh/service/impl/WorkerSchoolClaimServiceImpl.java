package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerSchoolClaimMapper;
import com.aorun.ymgh.model.WorkerSchoolClaim;
import com.aorun.ymgh.service.WorkerSchoolClaimService;
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
public class WorkerSchoolClaimServiceImpl implements WorkerSchoolClaimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerSchoolClaimServiceImpl.class);

    @Autowired
    private WorkerSchoolClaimMapper workerSchoolClaimMapper;

    @Override
    public WorkerSchoolClaim findWorkerSchoolClaimById(Long id) {
        return workerSchoolClaimMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerSchoolClaim(WorkerSchoolClaim workerSchoolClaim) {
        return workerSchoolClaimMapper.insert(workerSchoolClaim);
    }

    @Override
    public int updateWorkerSchoolClaim(WorkerSchoolClaim workerSchoolClaim) {
        return workerSchoolClaimMapper.updateByPrimaryKeySelective(workerSchoolClaim);
    }

    @Override
    public int deleteWorkerSchoolClaim(Long id) {
        return workerSchoolClaimMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerSchoolClaim> getWorkerSchoolClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerSchoolClaimMapper.getWorkerSchoolClaimListByWorkerId(workerId, start, limit);
    }


    @Override
    public void updateIsReadedStatus(Long id) {
        workerSchoolClaimMapper.updateIsReadedStatus(id);
    }

    @Override
    public List<WorkerSchoolClaim> getUnReadList(Long workerId) {
        return workerSchoolClaimMapper.getUnReadList(workerId);
    }

}
