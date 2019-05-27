package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerLegalAid;

import java.util.List;

/**
 * 法律援助-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerLegelAidService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerLegalAid findWorkerLegelAidById(Long id);

    /**
     * 新增信息
     *
     * @param workerLegalAid
     * @return
     */
    int saveWorkerLegelAid(WorkerLegalAid workerLegalAid);

    /**
     * 更新信息
     *
     * @param workerLegalAid
     * @return
     */
    int updateWorkerLegelAid(WorkerLegalAid workerLegalAid);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerLegelAid(Long id);


    List<WorkerLegalAid> getWorkerLegelAidListByWorkerId(Long workerId, Integer pageIndex, Integer pageSize);

}
