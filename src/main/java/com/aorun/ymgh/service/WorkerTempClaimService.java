package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerTempClaim;

import java.util.List;

/**
 * 临时救助-业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerTempClaimService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerTempClaim findWorkerTempClaimById(Long id);

    /**
     * 新增信息
     *
     * @param workerTempClaim
     * @return
     */
    int saveWorkerTempClaim(WorkerTempClaim workerTempClaim);

    /**
     * 更新信息
     *
     * @param workerTempClaim
     * @return
     */
    int updateWorkerTempClaim(WorkerTempClaim workerTempClaim);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerTempClaim(Long id);


    List<WorkerTempClaim> getWorkerTempClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

}
