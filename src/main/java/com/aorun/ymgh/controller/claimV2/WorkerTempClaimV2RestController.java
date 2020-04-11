package com.aorun.ymgh.controller.claimV2;


import com.aorun.ymgh.dto.WorkerTempClaimDto;
import com.aorun.ymgh.model.Resource;
import com.aorun.ymgh.model.WorkerTempClaim;
import com.aorun.ymgh.service.ResourceService;
import com.aorun.ymgh.service.WorkerTempClaimService;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.ImagePathUtil;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.UnionConstant;
import com.aorun.ymgh.util.biz.WorkerMemberUtil;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 临时救助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/workerV2")
@RestController
public class WorkerTempClaimV2RestController {

    @Autowired
    private WorkerTempClaimService workerTempClaimService;
    @Autowired
    private ResourceService resourceService;


    //1.列表接口----分页查询
    @RequestMapping(value = "/workerTempClaimList", method = RequestMethod.GET)
    public Object workerTempClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerTempClaim> workerTempClaimList = new ArrayList<WorkerTempClaim>();
        List<WorkerTempClaimDto> workerTempClaimDtoList = new ArrayList<WorkerTempClaimDto>();
        workerTempClaimList = workerTempClaimService.getWorkerTempClaimListByWorkerId(workerId, pageIndex, pageSize);
        for (WorkerTempClaim workerTempClaim : workerTempClaimList) {
            WorkerTempClaimDto workerTempClaimDto = new WorkerTempClaimDto();
            BeanUtils.copyProperties(workerTempClaim, workerTempClaimDto);
            workerTempClaimDto.setCreateTime(DateFormat.dateToString3(workerTempClaim.getCreateTime()));
            List<Resource> resourceList = resourceService.selectArticle(UnionConstant.RESOURCE_ARTICLE_CODE_TEMP_CLAIM, workerTempClaimDto.getId() + "");
            List<Map<String,Object>> resMapList = new ArrayList<Map<String,Object>>();
            for(Resource resource:resourceList){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("resId",resource.getId());
                map.put("resCode",resource.getResCode());
                map.put("tag",resource.getTag());
                map.put("url", ImagePathUtil.setFilePathStringServerName(resource.getUrl()));
                resMapList.add(map);
            }
            workerTempClaimDto.setResMapList(resMapList);
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
        BeanUtils.copyProperties(workerTempClaim, workerTempClaimDto);
        workerTempClaimDto.setCreateTime(DateFormat.dateToString3(workerTempClaim.getCreateTime()));
        List<Resource> resourceList = resourceService.selectArticle(UnionConstant.RESOURCE_ARTICLE_CODE_TEMP_CLAIM, workerTempClaimDto.getId() + "");
        List<Map<String,Object>> resMapList = new ArrayList<Map<String,Object>>();
        for(Resource resource:resourceList){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("resId",resource.getId());
            map.put("resCode",resource.getResCode());
            map.put("tag",resource.getTag());
            map.put("url", ImagePathUtil.setFilePathStringServerName(resource.getUrl()));
            resMapList.add(map);
        }
        workerTempClaimDto.setResMapList(resMapList);
        return Jsonp_data.success(workerTempClaimDto);
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
    @RequestMapping(value = "/workerTempClaim", method = RequestMethod.POST)
    public Object createWorkerTempClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                        @RequestParam(name = "population", required = true, defaultValue = "") Integer population,
                                        @RequestParam(name = "income", required = true, defaultValue = "") Integer income,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "mainReason", required = true, defaultValue = "") String mainReason,
                                        @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                        @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                        @RequestParam(name = "explainImgUrls", required = false) String explainImgUrls,
                                        @RequestParam(name = "ids", required = false) String ids) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        WorkerTempClaim workerTempClaim = new WorkerTempClaim();
        workerTempClaim.setExplainImgUrls(ImagePathUtil.getFileStringName(explainImgUrls));
        workerTempClaim.setWorkerId(workerId);
        workerTempClaim.setName(name);
        workerTempClaim.setPopulation(population);
        workerTempClaim.setIncome(income);
        workerTempClaim.setCompanyName(companyName);
        workerTempClaim.setTelephone(telephone);
        workerTempClaim.setMainReason(mainReason);
        workerTempClaim.setBankName(bankName);
        workerTempClaim.setCardNumber(cardNumber);
        workerTempClaimService.saveWorkerTempClaim(workerTempClaim);
        if(null!=ids&&!"".equals(ids)) {
            resourceService.updateBatchByPrimaryKeySelective(ids, UnionConstant.RESOURCE_ARTICLE_CODE_TEMP_CLAIM, workerTempClaim.getId() + "");
        }
        return Jsonp.success();
    }


    //修改接口
    @RequestMapping(value = "/updateWorkerTempClaim", method = RequestMethod.POST)
    public Object updateWorkerTempClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(value = "id") Long id,
                                        @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                        @RequestParam(name = "population", required = true, defaultValue = "") Integer population,
                                        @RequestParam(name = "income", required = true, defaultValue = "") Integer income,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "mainReason", required = true, defaultValue = "") String mainReason,
                                        @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                        @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                        @RequestParam(name = "explainImgUrls", required = false, defaultValue = "") String explainImgUrls,
                                        @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles,
                                        @RequestParam(name = "ids", required = false) String ids
                                        ) {
        WorkerTempClaim workerTempClaim = workerTempClaimService.findWorkerTempClaimById(id);

        workerTempClaim.setExplainImgUrls(ImagePathUtil.getFileStringName(explainImgUrls));
        workerTempClaim.setStatus(1);
        workerTempClaim.setName(name);
        workerTempClaim.setPopulation(population);
        workerTempClaim.setIncome(income);
        workerTempClaim.setCompanyName(companyName);
        workerTempClaim.setTelephone(telephone);
        workerTempClaim.setMainReason(mainReason);
        workerTempClaim.setBankName(bankName);
        workerTempClaim.setCardNumber(cardNumber);
        workerTempClaimService.updateWorkerTempClaim(workerTempClaim);
        if(null!=ids&&!"".equals(ids)) {
            resourceService.updateBatchByPrimaryKeySelective(ids, UnionConstant.RESOURCE_ARTICLE_CODE_TEMP_CLAIM, workerTempClaim.getId() + "");
        }
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
