package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerCardApplyMapper;
import com.aorun.ymgh.model.WorkerCardApply;
import com.aorun.ymgh.service.WorkerCardApplyService;
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
public class WorkerCardApplyServiceImpl implements WorkerCardApplyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerCardApplyServiceImpl.class);

    @Autowired
    private WorkerCardApplyMapper workerCardApplyMapper;

    @Override
    public WorkerCardApply findWorkerCardApplyById(Long id) {
        return workerCardApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerCardApply(WorkerCardApply workerCardApply) {
        return workerCardApplyMapper.insert(workerCardApply);
    }

    @Override
    public int updateWorkerCardApply(WorkerCardApply workerCardApply) {
        return workerCardApplyMapper.updateByPrimaryKeySelective(workerCardApply);
    }

    @Override
    public int deleteWorkerCardApply(Long id) {
        return workerCardApplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerCardApply> getWorkerCardApplyListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        //return workerCardApplyMapper.getWorkerCardApplyListByWorkerId(workerId,start,limit);
        return null;
    }

    @Override
    public WorkerCardApply findWorkerCardApplyByWorkerIdAndCardId(Long workerId, Long workerCardId) {
        return workerCardApplyMapper.findWorkerCardApplyByWorkerIdAndCardId(workerId,workerCardId);
    }

}
