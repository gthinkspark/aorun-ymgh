package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerSchoolClaim;

import java.util.List;

/**
 * 助学救助-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerSchoolClaimService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerSchoolClaim findWorkerSchoolClaimById(Long id);

    /**
     * 新增信息
     *
     * @param workerSchoolClaim
     * @return
     */
    int saveWorkerSchoolClaim(WorkerSchoolClaim workerSchoolClaim);

    /**
     * 更新信息
     *
     * @param workerSchoolClaim
     * @return
     */
    int updateWorkerSchoolClaim(WorkerSchoolClaim workerSchoolClaim);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerSchoolClaim(Long id);


    List<WorkerSchoolClaim> getWorkerSchoolClaimListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

    void updateIsReadedStatus(Long id);

    List<WorkerSchoolClaim> getUnReadList(Long workerId);

}
