package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerCardMapper;
import com.aorun.ymgh.model.WorkerCardWithBLOBs;
import com.aorun.ymgh.service.WorkerCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 普惠卡--逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerCardServiceImpl implements WorkerCardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerCardServiceImpl.class);

    @Autowired
    private WorkerCardMapper workerCardMapper;

    @Override
    public WorkerCardWithBLOBs findWorkerCardWithBLOBsById(Long id) {
        return workerCardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerCardWithBLOBs(WorkerCardWithBLOBs workerCardWithBLOBs) {
        return workerCardMapper.insert(workerCardWithBLOBs);
    }

    @Override
    public int updateWorkerCardWithBLOBs(WorkerCardWithBLOBs workerCardWithBLOBs) {
        return workerCardMapper.updateByPrimaryKeySelective(workerCardWithBLOBs);
    }

    @Override
    public int deleteWorkerCardWithBLOBs(Long id) {
        return workerCardMapper.deleteByPrimaryKey(id);
    }


//    @Override
//    public List<WorkerCard> getWorkerCardListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize) {
//        ///** 启始页-位置 */
//        Integer start = (pageIndex - 1) * pageSize;
//        /** 每页大小  */
//        Integer limit = pageSize;
//        //return workerCardMapper.getWorkerCardListByWorkerId(workerId,start,limit);
//        return  null;
//    }

}
