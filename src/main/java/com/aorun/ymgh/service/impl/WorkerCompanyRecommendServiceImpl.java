package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerCompanyRecommendMapper;
import com.aorun.ymgh.model.WorkerCompanyRecommend;
import com.aorun.ymgh.service.WorkerCompanyRecommendService;
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
public class WorkerCompanyRecommendServiceImpl implements WorkerCompanyRecommendService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerCompanyRecommendServiceImpl.class);

    @Autowired
    private WorkerCompanyRecommendMapper workerCompanyRecommendMapper;

    @Override
    public WorkerCompanyRecommend findWorkerCompanyRecommendById(Long id) {
        return workerCompanyRecommendMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerCompanyRecommend(WorkerCompanyRecommend workerCompanyRecommend) {
        return workerCompanyRecommendMapper.insert(workerCompanyRecommend);
    }

    @Override
    public int updateWorkerCompanyRecommend(WorkerCompanyRecommend workerCompanyRecommend) {
        return workerCompanyRecommendMapper.updateByPrimaryKeySelective(workerCompanyRecommend);
    }

    @Override
    public int deleteWorkerCompanyRecommend(Long id) {
        return workerCompanyRecommendMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerCompanyRecommend> getWorkerCompanyRecommendList(Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerCompanyRecommendMapper.getWorkerCompanyRecommendList(start, limit);
    }

}
