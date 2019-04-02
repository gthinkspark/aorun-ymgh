package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerApplyClaim;

import java.util.List;

/**
 * 申请理赔-业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerApplyClaimService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerApplyClaim findWorkerApplyClaimById(Long id);

    /**
     * 新增信息
     *
     * @param workerApplyClaim
     * @return
     */
    int saveWorkerApplyClaim(WorkerApplyClaim workerApplyClaim);

    /**
     * 更新信息
     *
     * @param workerApplyClaim
     * @return
     */
    int updateWorkerApplyClaim(WorkerApplyClaim workerApplyClaim);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerApplyClaim(Long id);


    List<WorkerApplyClaim> getWorkerApplyClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

}
