package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerCompanyRecommend;

import java.util.List;

/**
 * 精选商家推荐-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerCompanyRecommendService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerCompanyRecommend findWorkerCompanyRecommendById(Long id);

    /**
     * 新增信息
     *
     * @param workerCompanyRecommend
     * @return
     */
    int saveWorkerCompanyRecommend(WorkerCompanyRecommend workerCompanyRecommend);

    /**
     * 更新信息
     *
     * @param workerCompanyRecommend
     * @return
     */
    int updateWorkerCompanyRecommend(WorkerCompanyRecommend workerCompanyRecommend);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerCompanyRecommend(Long id);


    List<WorkerCompanyRecommend> getWorkerCompanyRecommendList(Integer pageIndex, Integer pageSize);

}
