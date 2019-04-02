package com.aorun.ymgh.controller;


import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.dto.WorkerSchoolClaimDto;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.model.WorkerSchoolClaim;
import com.aorun.ymgh.service.WorkerSchoolClaimService;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.biz.UnionUtil;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *助学救助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerSchoolClaimRestController {

    @Autowired
    private WorkerSchoolClaimService workerSchoolClaimService;


        //1.列表接口----分页查询
        @RequestMapping(value = "/workerSchoolClaimList", method = RequestMethod.GET)
        public Object workerSchoolClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name="pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name="pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
            ) {


            UserDto user = null;
            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) RedisCache.get(sid);
                if (CheckObjectIsNull.isNull(user)) {
                    return Jsonp.noLoginError("请先登录或重新登录");
                }
                workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user), WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    return Jsonp.noAccreditError("用户未授权工会,请重新授权");
                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }

        Long workerId = workerMember.getId();
        List<WorkerSchoolClaim>   workerSchoolClaimList = new ArrayList<WorkerSchoolClaim>();
        List<WorkerSchoolClaimDto>   workerSchoolClaimDtoList = new ArrayList<WorkerSchoolClaimDto>();
        workerSchoolClaimList = workerSchoolClaimService.getWorkerSchoolClaimListByWorkerId(workerId,pageIndex,pageSize);
        for(WorkerSchoolClaim workerSchoolClaim:workerSchoolClaimList){
            WorkerSchoolClaimDto workerSchoolClaimDto = new WorkerSchoolClaimDto();
            BeanUtils.copyProperties(workerSchoolClaim,workerSchoolClaimDto);
            workerSchoolClaimDto.setCreateTime(DateFormat.dateToString3(workerSchoolClaim.getCreateTime()));
            workerSchoolClaimDtoList.add(workerSchoolClaimDto);
        }
        return Jsonp_data.success(workerSchoolClaimDtoList);
    }

    //3.详情接口
    @RequestMapping(value = "/workerSchoolClaim/{id}", method = RequestMethod.GET)
    public Object findOneWorkerSchoolClaim(@PathVariable("id") Long id,
     @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {

        WorkerSchoolClaim workerSchoolClaim = workerSchoolClaimService.findWorkerSchoolClaimById(id);
        WorkerSchoolClaimDto workerSchoolClaimDto = new WorkerSchoolClaimDto();
        BeanUtils.copyProperties(workerSchoolClaim,workerSchoolClaimDto);
        workerSchoolClaimDto.setCreateTime(DateFormat.dateToString3(workerSchoolClaim.getCreateTime()));
        return Jsonp_data.success(workerSchoolClaimDto);
    }

    //2.新增接口
    @RequestMapping(value = "/workerSchoolClaim", method = RequestMethod.POST)
    public Object createWorkerSchoolClaim(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestBody WorkerSchoolClaim workerSchoolClaim) {

        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
            if (CheckObjectIsNull.isNull(user)) {
                return Jsonp.noLoginError("请先登录或重新登录");
            }
            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
            if (CheckObjectIsNull.isNull(workerMember)) {
                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
            }
        } else {
            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
        }

        Long workerId = workerMember.getId();
        workerSchoolClaim.setWorkerId(workerId);
        workerSchoolClaimService.saveWorkerSchoolClaim(workerSchoolClaim);
        return Jsonp.success();
    }


    //修改接口
    @RequestMapping(value = "/workerSchoolClaim", method = RequestMethod.PUT)
    public Object updateWorkerSchoolClaim(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                            @RequestBody WorkerSchoolClaim workerSchoolClaim) {

        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
            if (CheckObjectIsNull.isNull(user)) {
                return Jsonp.noLoginError("请先登录或重新登录");
            }
            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
            if (CheckObjectIsNull.isNull(workerMember)) {
                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
            }
        } else {
            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
        }

        workerSchoolClaim.setStatus(1);
        workerSchoolClaimService.updateWorkerSchoolClaim(workerSchoolClaim);
        return Jsonp.success();
    }

}
