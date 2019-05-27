package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerMedicalClaim;

import java.util.List;

/**
 * 医疗救助-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerMedicalClaimService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerMedicalClaim findWorkerMedicalClaimById(Long id);

    /**
     * 新增信息
     *
     * @param workerMedicalClaim
     * @return
     */
    int saveWorkerMedicalClaim(WorkerMedicalClaim workerMedicalClaim);

    /**
     * 更新信息
     *
     * @param workerMedicalClaim
     * @return
     */
    int updateWorkerMedicalClaim(WorkerMedicalClaim workerMedicalClaim);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerMedicalClaim(Long id);


    List<WorkerMedicalClaim> getWorkerMedicalClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

    void updateIsReadedStatus(Long id);

    List<WorkerMedicalClaim> getUnReadList(Long workerId);

}
