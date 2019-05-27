package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.WorkerMemberMapper;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.service.WorkerMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerMemberServiceImpl implements WorkerMemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerMemberServiceImpl.class);

    @Autowired
    private WorkerMemberMapper workerMemberMapper;

    @Override
    public WorkerMember findWorkerMemberById(Long id) {
        return workerMemberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerMember(WorkerMember workerMember) {
        return workerMemberMapper.insert(workerMember);
    }

    @Override
    public int updateWorkerMember(WorkerMember workerMember) {
        return workerMemberMapper.updateByPrimaryKeySelective(workerMember);
    }

    @Override
    public int deleteWorkerMember(Long id) {
        return workerMemberMapper.deleteByPrimaryKey(id);
    }


}
