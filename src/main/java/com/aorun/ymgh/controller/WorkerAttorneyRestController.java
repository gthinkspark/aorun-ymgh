package com.aorun.ymgh.controller;


import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.controller.login.WorkerMember;
import com.aorun.ymgh.dto.WorkerAttorneyDto;
import com.aorun.ymgh.model.WorkerAttorney;
import com.aorun.ymgh.service.WorkerAttorneyService;
import com.aorun.ymgh.util.CheckObjectIsNull;
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
 *
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerAttorneyRestController {

    @Autowired
    private WorkerAttorneyService workerAttorneyService;


        //1.列表接口----分页查询
        @RequestMapping(value = "/workerAttorneyList", method = RequestMethod.GET)
        public Object workerLiveClaimList(
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
                workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    return Jsonp.noAccreditError("用户未授权工会,请重新授权");
                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }

        Long workerId = workerMember.getId();
        List<WorkerAttorney>   workerAttorneyList = new ArrayList<WorkerAttorney>();
        List<WorkerAttorneyDto>   workerAttorneyDtoList = new ArrayList<WorkerAttorneyDto>();
            workerAttorneyList = workerAttorneyService.getWorkerAttorneyListByWorkerId(workerId,pageIndex,pageSize);
        for(WorkerAttorney workerAttorney:workerAttorneyList){
            WorkerAttorneyDto workerAttorneyDto = new WorkerAttorneyDto();
            BeanUtils.copyProperties(workerAttorney,workerAttorneyDto);
            //workerAttorneyDto.setCreateTime(DateFormat.dateToString3(workerAttorney.getCreateTime()));
            workerAttorneyDtoList.add(workerAttorneyDto);
        }
        return Jsonp_data.success(workerAttorneyDtoList);
    }

    //3.详情接口
    @RequestMapping(value = "/workerAttorney/{id}", method = RequestMethod.GET)
    public Object findOneWorkerLiveClaim(@PathVariable("id") Long id,
     @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {

        WorkerAttorney workerAttorney = workerAttorneyService.findWorkerAttorneyById(id);
        WorkerAttorneyDto workerAttorneyDto = new WorkerAttorneyDto();
        BeanUtils.copyProperties(workerAttorney,workerAttorneyDto);
        //workerAttorneyDto.setCreateTime(DateFormat.dateToString3(workerAttorney.getCreateTime()));
        return Jsonp_data.success(workerAttorneyDto);
    }

    //2.新增接口
    @RequestMapping(value = "/workerAttorney", method = RequestMethod.POST)
    public Object createWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestBody WorkerAttorney workerAttorney) {

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
        //workerAttorney.setWorkerId(workerId);
        workerAttorneyService.saveWorkerAttorney(workerAttorney);
        return Jsonp.success();
    }

}
