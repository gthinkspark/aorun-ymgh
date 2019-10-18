package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerLiveClaimMapper;
import com.aorun.ymgh.model.WorkerLiveClaim;
import com.aorun.ymgh.service.WorkerLiveClaimService;
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
public class WorkerLiveClaimServiceImpl implements WorkerLiveClaimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerLiveClaimServiceImpl.class);

    @Autowired
    private WorkerLiveClaimMapper workerLiveClaimMapper;


    @Override
    public WorkerLiveClaim findWorkerLiveClaimById(Long id) {
        return workerLiveClaimMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerLiveClaim(WorkerLiveClaim workerLiveClaim) {
        return workerLiveClaimMapper.insert(workerLiveClaim);
    }

    @Override
    public int updateWorkerLiveClaim(WorkerLiveClaim workerLiveClaim) {
        return workerLiveClaimMapper.updateByPrimaryKeySelective(workerLiveClaim);
    }

    @Override
    public int deleteWorkerLiveClaim(Long id) {
        return workerLiveClaimMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerLiveClaim> getWorkerLiveClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {

        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerLiveClaimMapper.getWorkerLiveClaimListByWorkerId(workerId, start, limit);
    }

    @Override
    public void updateIsReadedStatus(Long id) {
        workerLiveClaimMapper.updateIsReadedStatus(id);
    }

    @Override
    public List<WorkerLiveClaim> getUnReadList(Long workerId) {
        return workerLiveClaimMapper.getUnReadList(workerId);
    }

    @Override
    public List<WorkerLiveClaim> getAllList() {
        return workerLiveClaimMapper.getAllList();
    }


}
