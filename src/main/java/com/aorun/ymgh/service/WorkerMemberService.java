package com.aorun.ymgh.service;


import com.aorun.ymgh.model.WorkerMember;

/**
 * 法律援助-业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerMemberService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerMember findWorkerMemberById(Long id);

    /**
     * 新增信息
     *
     * @param workerMember
     * @return
     */
    int saveWorkerMember(WorkerMember workerMember);

    /**
     * 更新信息
     *
     * @param workerMember
     * @return
     */
    int updateWorkerMember(WorkerMember workerMember);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerMember(Long id);
}
