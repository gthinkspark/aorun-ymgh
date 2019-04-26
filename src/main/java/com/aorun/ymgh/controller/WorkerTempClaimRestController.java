package com.aorun.ymgh.controller;


import com.aorun.ymgh.dto.WorkerTempClaimDto;
import com.aorun.ymgh.model.WorkerTempClaim;
import com.aorun.ymgh.service.WorkerTempClaimService;
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
 *临时救助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerTempClaimRestController {

    @Autowired
    private WorkerTempClaimService workerTempClaimService;

        //1.列表接口----分页查询
        @RequestMapping(value = "/workerTempClaimList", method = RequestMethod.GET)
        public Object workerTempClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name="pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name="pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
            ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerTempClaim>   workerTempClaimList = new ArrayList<WorkerTempClaim>();
        List<WorkerTempClaimDto>   workerTempClaimDtoList = new ArrayList<WorkerTempClaimDto>();
        workerTempClaimList = workerTempClaimService.getWorkerTempClaimListByWorkerId(workerId,pageIndex,pageSize);
        for(WorkerTempClaim workerTempClaim:workerTempClaimList){
            WorkerTempClaimDto workerTempClaimDto = new WorkerTempClaimDto();
            BeanUtils.copyProperties(workerTempClaim,workerTempClaimDto);
            workerTempClaimDto.setCreateTime(DateFormat.dateToString3(workerTempClaim.getCreateTime()));
            workerTempClaimDtoList.add(workerTempClaimDto);
        }
        return Jsonp_data.success(workerTempClaimDtoList);
    }

    //3.详情接口
    @RequestMapping(value = "/workerTempClaim/{id}", method = RequestMethod.GET)
    public Object findOneWorkerTempClaim(@PathVariable("id") Long id,
     @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {

        WorkerTempClaim workerTempClaim = workerTempClaimService.findWorkerTempClaimById(id);
        WorkerTempClaimDto workerTempClaimDto = new WorkerTempClaimDto();
        BeanUtils.copyProperties(workerTempClaim,workerTempClaimDto);
        workerTempClaimDto.setCreateTime(DateFormat.dateToString3(workerTempClaim.getCreateTime()));
        return Jsonp_data.success(workerTempClaimDto);
    }

    //2.新增接口
    @RequestMapping(value = "/workerTempClaim", method = RequestMethod.POST)
    public Object createWorkerTempClaim(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestBody WorkerTempClaim workerTempClaim) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        workerTempClaim.setWorkerId(workerId);
        workerTempClaimService.saveWorkerTempClaim(workerTempClaim);
        return Jsonp.success();
    }


    //修改接口
    @RequestMapping(value = "/updateWorkerTempClaim", method = RequestMethod.POST)
    public Object updateWorkerTempClaim(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestBody WorkerTempClaim workerTempClaim) {
        workerTempClaim.setStatus(1);
        workerTempClaimService.updateWorkerTempClaim(workerTempClaim);
        return Jsonp.success();
    }

    //已读接口
    @RequestMapping(value = "/workerTempClaimRead/{id}", method = RequestMethod.GET)
    public Object workerTempClaimRead(
            @PathVariable("id") Long id,
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
            ) {
        workerTempClaimService.updateIsReadedStatus(id);
        return Jsonp.success();
    }

}
