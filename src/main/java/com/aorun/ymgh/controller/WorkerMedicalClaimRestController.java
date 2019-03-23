package com.aorun.ymgh.controller;


import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.controller.login.WorkerMember;
import com.aorun.ymgh.dto.WorkerMedicalClaimDto;
import com.aorun.ymgh.model.WorkerMedicalClaim;
import com.aorun.ymgh.service.WorkerMedicalClaimService;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.UnionUtil;
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
public class WorkerMedicalClaimRestController {

    @Autowired
    private WorkerMedicalClaimService workerMedicalClaimService;


        //1.列表接口----分页查询
        @RequestMapping(value = "/workerMedicalClaimList", method = RequestMethod.GET)
        public Object workerMedicalClaimList(
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
        List<WorkerMedicalClaim>   workerMedicalClaimList = new ArrayList<WorkerMedicalClaim>();
        List<WorkerMedicalClaimDto>   workerMedicalClaimDtoList = new ArrayList<WorkerMedicalClaimDto>();
        workerMedicalClaimList = workerMedicalClaimService.getWorkerMedicalClaimListByWorkerId(workerId,pageIndex,pageSize);
        for(WorkerMedicalClaim workerMedicalClaim:workerMedicalClaimList){
            WorkerMedicalClaimDto workerMedicalClaimDto = new WorkerMedicalClaimDto();
            BeanUtils.copyProperties(workerMedicalClaim,workerMedicalClaimDto);
            workerMedicalClaimDto.setCreateTime(DateFormat.dateToString3(workerMedicalClaim.getCreateTime()));
            workerMedicalClaimDtoList.add(workerMedicalClaimDto);
        }
        return Jsonp_data.success(workerMedicalClaimDtoList);
    }

    //3.详情接口
    @RequestMapping(value = "/workerMedicalClaim/{id}", method = RequestMethod.GET)
    public Object findOneWorkerMedicalClaim(@PathVariable("id") Long id,
     @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {

        WorkerMedicalClaim workerMedicalClaim = workerMedicalClaimService.findWorkerMedicalClaimById(id);
        WorkerMedicalClaimDto workerMedicalClaimDto = new WorkerMedicalClaimDto();
        BeanUtils.copyProperties(workerMedicalClaim,workerMedicalClaimDto);
        workerMedicalClaimDto.setCreateTime(DateFormat.dateToString3(workerMedicalClaim.getCreateTime()));
        return Jsonp_data.success(workerMedicalClaimDto);
    }

    //2.新增接口
    @RequestMapping(value = "/workerMedicalClaim", method = RequestMethod.POST)
    public Object createWorkerMedicalClaim(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestBody WorkerMedicalClaim workerMedicalClaim) {

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
        workerMedicalClaim.setWorkerId(workerId);
        workerMedicalClaimService.saveWorkerMedicalClaim(workerMedicalClaim);
        return Jsonp.success();
    }

}
