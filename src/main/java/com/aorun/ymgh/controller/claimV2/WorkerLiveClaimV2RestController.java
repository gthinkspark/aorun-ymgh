package com.aorun.ymgh.controller.claimV2;


import com.aorun.ymgh.dto.WorkerLiveClaimDto;
import com.aorun.ymgh.model.Resource;
import com.aorun.ymgh.model.WorkerLiveClaim;
import com.aorun.ymgh.service.ResourceService;
import com.aorun.ymgh.service.WorkerLiveClaimService;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.ImagePathUtil;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.UnionConstant;
import com.aorun.ymgh.util.biz.WorkerMemberUtil;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生活援助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/workerV2")
@RestController
public class WorkerLiveClaimV2RestController {

    @Autowired
    private WorkerLiveClaimService workerLiveClaimService;
    @Autowired
    private ResourceService resourceService;


    //1.列表接口----分页查询
    @RequestMapping(value = "/workerLiveClaimList", method = RequestMethod.GET)
    public Object workerLiveClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerLiveClaim> workerLiveClaimList = new ArrayList<WorkerLiveClaim>();
        List<WorkerLiveClaimDto> workerLiveClaimDtoList = new ArrayList<WorkerLiveClaimDto>();
        workerLiveClaimList = workerLiveClaimService.getWorkerLiveClaimListByWorkerId(workerId, pageIndex, pageSize);
        for (WorkerLiveClaim workerLiveClaim : workerLiveClaimList) {
            WorkerLiveClaimDto workerLiveClaimDto = new WorkerLiveClaimDto();
            BeanUtils.copyProperties(workerLiveClaim, workerLiveClaimDto);
            workerLiveClaimDto.setCreateTime(DateFormat.dateToString3(workerLiveClaim.getCreateTime()));
            //列表不需要图片展示
//            List<Resource> resourceList = resourceService.selectArticle(UnionConstant.RESOURCE_ARTICLE_CODE_LIVE_CLAIM, workerLiveClaim.getId() + "");
//            List<Map<String,Object>> resMapList = new ArrayList<Map<String,Object>>();
//            for(Resource resource:resourceList){
//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("resId",resource.getId());
//                map.put("resCode",resource.getResCode());
//                map.put("tag",resource.getTag());
//                map.put("url",ImagePathUtil.setFilePathStringServerName(resource.getUrl()));
//                resMapList.add(map);
//            }
//            workerLiveClaimDto.setResMapList(resMapList);
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
        BeanUtils.copyProperties(workerLiveClaim, workerLiveClaimDto);
        workerLiveClaimDto.setCreateTime(DateFormat.dateToString3(workerLiveClaim.getCreateTime()));
        List<Map<String, Object>> resMapList = new ArrayList<Map<String, Object>>();
        if(!StringUtils.isEmpty(workerLiveClaimDto.getResIds())) {
            List<Resource> resourceList = resourceService.selectByIds(workerLiveClaimDto.getResIds());
            for (Resource resource : resourceList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("resId", resource.getId());
                map.put("resCode", resource.getResCode());
                map.put("tag", resource.getTag());
                map.put("url", ImagePathUtil.setFilePathStringServerName(resource.getUrl()));
                resMapList.add(map);
            }
        }
        workerLiveClaimDto.setResMapList(resMapList);
        return Jsonp_data.success(workerLiveClaimDto);
    }


    /*
     {
         "name": "123",
             "population": 5,
             "income": 10,
             "companyName": "12345679810",
             "telephone": "12345679810",
             "mainReason": "12345679810"
     }
     */
    //2.新增接口
    @RequestMapping(value = "/workerLiveClaim", method = RequestMethod.POST)
    public Object createWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                        @RequestParam(name = "population", required = true, defaultValue = "") Integer population,
                                        @RequestParam(name = "income", required = true, defaultValue = "") Integer income,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "mainReason", required = true, defaultValue = "") String mainReason,
                                        @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                        @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                        @RequestParam(name = "explainImgUrls", required = true) String explainImgUrls,
                                        @RequestParam(name = "ids", required = true) String ids
    ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        WorkerLiveClaim workerLiveClaim = new WorkerLiveClaim();
        workerLiveClaim.setWorkerId(workerId);
        workerLiveClaim.setName(name);
        workerLiveClaim.setPopulation(population);
        workerLiveClaim.setIncome(income);
        workerLiveClaim.setCompanyName(companyName);
        workerLiveClaim.setTelephone(telephone);
        workerLiveClaim.setMainReason(mainReason);
        workerLiveClaim.setBankName(bankName);
        workerLiveClaim.setCardNumber(cardNumber);
//        workerLiveClaim.setExplainImgUrls(ImagePathUtil.getFileStringName(explainImgUrls));
        workerLiveClaim.setResIds(ids);
        workerLiveClaimService.saveWorkerLiveClaim(workerLiveClaim);
        if(null!=ids&&!"".equals(ids)){
            resourceService.updateBatchByPrimaryKeySelective(ids, UnionConstant.RESOURCE_ARTICLE_CODE_LIVE_CLAIM,workerLiveClaim.getId()+"");
        }
        return Jsonp.success();
    }

    //修改接口
    @RequestMapping(value = "/updateWorkerLiveClaim", method = RequestMethod.POST)
    public Object updateWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(value = "id") Long id,
                                        @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                        @RequestParam(name = "population", required = true, defaultValue = "") Integer population,
                                        @RequestParam(name = "income", required = true, defaultValue = "") Integer income,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "mainReason", required = true, defaultValue = "") String mainReason,
                                        @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                        @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                        @RequestParam(name = "explainImgUrls", required = true, defaultValue = "") String explainImgUrls,
                                        @RequestParam(name = "ids", required = true) String ids
    ) {

        WorkerLiveClaim workerLiveClaim = workerLiveClaimService.findWorkerLiveClaimById(id);
        resourceService.delete(workerLiveClaim.getResIds());
        workerLiveClaim.setStatus(1);
        workerLiveClaim.setName(name);
        workerLiveClaim.setPopulation(population);
        workerLiveClaim.setIncome(income);
        workerLiveClaim.setCompanyName(companyName);
        workerLiveClaim.setTelephone(telephone);
        workerLiveClaim.setMainReason(mainReason);
        workerLiveClaim.setBankName(bankName);
        workerLiveClaim.setCardNumber(cardNumber);
//        workerLiveClaim.setExplainImgUrls(ImagePathUtil.getFileStringName(explainImgUrls));
        workerLiveClaim.setResIds(ids);
        workerLiveClaimService.updateWorkerLiveClaim(workerLiveClaim);
        if(null!=ids&&!"".equals(ids)) {
            resourceService.updateBatchByPrimaryKeySelective(ids, UnionConstant.RESOURCE_ARTICLE_CODE_LIVE_CLAIM, workerLiveClaim.getId() + "");
        }
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
