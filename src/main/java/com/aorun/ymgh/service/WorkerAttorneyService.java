package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerAttorney;

import java.util.List;

/**
 * 律师-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerAttorneyService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerAttorney findWorkerAttorneyById(Long id);

    /**
     * 新增信息
     *
     * @param workerAttorney
     * @return
     */
    int saveWorkerAttorney(WorkerAttorney workerAttorney);

    /**
     * 更新信息
     *
     * @param workerAttorney
     * @return
     */
    int updateWorkerAttorney(WorkerAttorney workerAttorney);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerAttorney(Long id);


    List<WorkerAttorney> getWorkerAttorneyListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

}
