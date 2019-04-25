package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerCardApply;

import java.util.List;

/**
 * 法律援助-业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerCardApplyService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerCardApply findWorkerCardApplyById(Long id);

    /**
     * 新增信息
     *
     * @param workerCardApply
     * @return
     */
    int saveWorkerCardApply(WorkerCardApply workerCardApply);

    /**
     * 更新信息
     *
     * @param workerCardApply
     * @return
     */
    int updateWorkerCardApply(WorkerCardApply workerCardApply);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerCardApply(Long id);


    List<WorkerCardApply> getWorkerCardApplyListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);


    /**
     * 根据 workerId,workerCardId  查询信息
     *
     * @param workerId
     * @param workerCardId
     */
    WorkerCardApply findWorkerCardApplyByWorkerIdAndCardId(Long workerId,Long workerCardId);

    void updateIsReadedStatus(Long id);
}
