package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerAttorneyReplyAdvisoryMapper;
import com.aorun.ymgh.model.WorkerAttorneyReplyAdvisory;
import com.aorun.ymgh.service.WorkerAttorneyReplyAdvisoryService;
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
public class WorkerAttorneyReplyAdvisoryServiceImpl implements WorkerAttorneyReplyAdvisoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerAttorneyReplyAdvisoryServiceImpl.class);

    @Autowired
    private WorkerAttorneyReplyAdvisoryMapper workerAttorneyReplyAdvisoryMapper;

    @Override
    public WorkerAttorneyReplyAdvisory findWorkerAttorneyReplyAdvisoryById(Long id) {
        return workerAttorneyReplyAdvisoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerAttorneyReplyAdvisory(WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory) {
        return workerAttorneyReplyAdvisoryMapper.insert(workerAttorneyReplyAdvisory);
    }

    @Override
    public int updateWorkerAttorneyReplyAdvisory(WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory) {
        return workerAttorneyReplyAdvisoryMapper.updateByPrimaryKeySelective(workerAttorneyReplyAdvisory);
    }

    @Override
    public int deleteWorkerAttorneyReplyAdvisory(Long id) {
        return workerAttorneyReplyAdvisoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerAttorneyReplyAdvisory> getWorkerAttorneyReplyAdvisoryListByWorkerId(Long workerId, Long advisoryId, String requestTimePoint, String isfirstPoint) {
        return workerAttorneyReplyAdvisoryMapper.getWorkerAttorneyReplyAdvisoryListByWorkerId(workerId, advisoryId, requestTimePoint, isfirstPoint);
    }
}
