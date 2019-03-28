package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerCardMapper;
import com.aorun.ymgh.model.WorkerCard;
import com.aorun.ymgh.service.WorkerCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerCardServiceImpl implements WorkerCardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerCardServiceImpl.class);

    @Autowired
    private WorkerCardMapper workerCardMapper;

    @Override
    public WorkerCard findWorkerCardById(Long id) {
        return workerCardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerCard(WorkerCard workerCard) {
        return workerCardMapper.insert(workerCard);
    }

    @Override
    public int updateWorkerCard(WorkerCard workerCard) {
        return workerCardMapper.updateByPrimaryKeySelective(workerCard);
    }

    @Override
    public int deleteWorkerCard(Long id) {
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
