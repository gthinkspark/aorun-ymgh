package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerCard;

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
    WorkerCard findWorkerCardById(Long id);

    /**
     * 新增信息
     *
     * @param workerCard
     * @return
     */
    int saveWorkerCard(WorkerCard workerCard);

    /**
     * 更新信息
     *
     * @param workerCard
     * @return
     */
    int updateWorkerCard(WorkerCard workerCard);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerCard(Long id);


    //List<WorkerCard> getWorkerCardListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

}
