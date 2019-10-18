package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerLiveClaim;

import java.util.List;

/**
 * 生活救助-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerLiveClaimService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerLiveClaim findWorkerLiveClaimById(Long id);

    /**
     * 新增信息
     *
     * @param workerLiveClaim
     * @return
     */
    int saveWorkerLiveClaim(WorkerLiveClaim workerLiveClaim);

    /**
     * 更新信息
     *
     * @param workerLiveClaim
     * @return
     */
    int updateWorkerLiveClaim(WorkerLiveClaim workerLiveClaim);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerLiveClaim(Long id);


    List<WorkerLiveClaim> getWorkerLiveClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

    void updateIsReadedStatus(Long id);

    List<WorkerLiveClaim> getUnReadList(Long workerId);

    List<WorkerLiveClaim> getAllList();

}
