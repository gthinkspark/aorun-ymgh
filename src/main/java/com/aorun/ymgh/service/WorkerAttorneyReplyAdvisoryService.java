package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerAttorneyReplyAdvisory;

import java.util.List;

/**
 * 援助-业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerAttorneyReplyAdvisoryService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerAttorneyReplyAdvisory findWorkerAttorneyReplyAdvisoryById(Long id);

    /**
     * 新增信息
     *
     * @param workerAttorneyReplyAdvisory
     * @return
     */
    int saveWorkerAttorneyReplyAdvisory(WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory);

    /**
     * 更新信息
     *
     * @param workerAttorneyReplyAdvisory
     * @return
     */
    int updateWorkerAttorneyReplyAdvisory(WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerAttorneyReplyAdvisory(Long id);


    List<WorkerAttorneyReplyAdvisory> getWorkerAttorneyReplyAdvisoryListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

}
