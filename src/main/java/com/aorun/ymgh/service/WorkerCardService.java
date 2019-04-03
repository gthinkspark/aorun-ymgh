package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerCardWithBLOBs;

/**
 * 工会卡表-业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerCardService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerCardWithBLOBs findWorkerCardWithBLOBsById(Long id);

    /**
     * 新增信息
     *
     * @param workerCardWithBLOBs
     * @return
     */
    int saveWorkerCardWithBLOBs(WorkerCardWithBLOBs workerCardWithBLOBs);

    /**
     * 更新信息
     *
     * @param workerCardWithBLOBs
     * @return
     */
    int updateWorkerCardWithBLOBs(WorkerCardWithBLOBs workerCardWithBLOBs);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerCardWithBLOBs(Long id);


    //List<WorkerCardWithBLOBs> getWorkerCardWithBLOBsListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

}
