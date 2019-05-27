package com.aorun.ymgh.util.biz;


import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.util.cache.redis.RedisCache;

/**
 * @author 作者 duxihu
 */
public class WorkerMemberUtil {

    public static Long getWorkerId(
            String sid) {
        UserDto user = (UserDto) RedisCache.get(sid);
        WorkerMember workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user), WorkerMember.class);
        return workerMember.getId();
    }
}
