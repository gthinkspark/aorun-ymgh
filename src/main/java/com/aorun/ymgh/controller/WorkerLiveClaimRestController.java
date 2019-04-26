package com.aorun.ymgh.controller;


import com.aorun.ymgh.dto.WorkerLiveClaimDto;
import com.aorun.ymgh.model.WorkerLiveClaim;
import com.aorun.ymgh.service.WorkerLiveClaimService;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.biz.WorkerMemberUtil;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *生活援助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerLiveClaimRestController {

    @Autowired
    private WorkerLiveClaimService workerLiveClaimService;


        //1.列表接口----分页查询
        @RequestMapping(value = "/workerLiveClaimList", method = RequestMethod.GET)
        public Object workerLiveClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name="pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name="pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
            ) {


            Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerLiveClaim>   workerLiveClaimList = new ArrayList<WorkerLiveClaim>();
        List<WorkerLiveClaimDto>   workerLiveClaimDtoList = new ArrayList<WorkerLiveClaimDto>();
        workerLiveClaimList = workerLiveClaimService.getWorkerLiveClaimListByWorkerId(workerId,pageIndex,pageSize);
        for(WorkerLiveClaim workerLiveClaim:workerLiveClaimList){
            WorkerLiveClaimDto workerLiveClaimDto = new WorkerLiveClaimDto();
            BeanUtils.copyProperties(workerLiveClaim,workerLiveClaimDto);
            workerLiveClaimDto.setCreateTime(DateFormat.dateToString3(workerLiveClaim.getCreateTime()));
            workerLiveClaimDtoList.add(workerLiveClaimDto);
        }
        return Jsonp_data.success(workerLiveClaimDtoList);
    }



    //3.详情接口
    @RequestMapping(value = "/workerLiveClaim/{id}", method = RequestMethod.GET)
    public Object findOneWorkerLiveClaim(@PathVariable("id") Long id,
     @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {

        WorkerLiveClaim workerLiveClaim = workerLiveClaimService.findWorkerLiveClaimById(id);
        WorkerLiveClaimDto workerLiveClaimDto = new WorkerLiveClaimDto();
        BeanUtils.copyProperties(workerLiveClaim,workerLiveClaimDto);
        workerLiveClaimDto.setCreateTime(DateFormat.dateToString3(workerLiveClaim.getCreateTime()));
        return Jsonp_data.success(workerLiveClaimDto);
    }

    //2.新增接口
    @RequestMapping(value = "/workerLiveClaim", method = RequestMethod.POST)
    public Object createWorkerLiveClaim(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestBody WorkerLiveClaim workerLiveClaim) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        workerLiveClaim.setWorkerId(workerId);
        workerLiveClaimService.saveWorkerLiveClaim(workerLiveClaim);
        return Jsonp.success();
    }

    //修改接口
    @RequestMapping(value = "/updateWorkerLiveClaim", method = RequestMethod.POST)
    public Object updateWorkerLiveClaim(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestBody WorkerLiveClaim workerLiveClaim) {
        workerLiveClaim.setStatus(1);
        workerLiveClaimService.updateWorkerLiveClaim(workerLiveClaim);
        return Jsonp.success();
    }


    //已读接口
    @RequestMapping(value = "/workerLiveClaimRead/{id}", method = RequestMethod.GET)
    public Object workerLiveClaimRead(
            @PathVariable("id") Long id,
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
    ) {
        workerLiveClaimService.updateIsReadedStatus(id);
        return Jsonp.success();
    }

}
