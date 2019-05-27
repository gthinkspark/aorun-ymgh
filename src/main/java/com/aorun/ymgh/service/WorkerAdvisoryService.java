package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerAdvisory;

import java.util.List;

/**
 * 法律咨询&我的留言-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerAdvisoryService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerAdvisory findWorkerAdvisoryById(Long id);

    /**
     * 新增信息
     *
     * @param workerAdvisory
     * @return
     */
    int saveWorkerAdvisory(WorkerAdvisory workerAdvisory);

    /**
     * 更新信息
     *
     * @param workerAdvisory
     * @return
     */
    int updateWorkerAdvisory(WorkerAdvisory workerAdvisory);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerAdvisory(Long id);


    List<WorkerAdvisory> getWorkerAdvisoryListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);


    List<WorkerAdvisory> getUnReadWorkerAdvisoryList(Long workerId);

    void updateIsReadedStatus(Long id);


}
