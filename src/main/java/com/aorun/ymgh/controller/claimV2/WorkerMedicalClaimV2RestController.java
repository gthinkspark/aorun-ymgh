package com.aorun.ymgh.controller.claimV2;


import com.aorun.ymgh.dto.WorkerMedicalClaimDto;
import com.aorun.ymgh.model.Resource;
import com.aorun.ymgh.model.WorkerMedicalClaim;
import com.aorun.ymgh.service.ResourceService;
import com.aorun.ymgh.service.WorkerMedicalClaimService;
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
 * 医疗援助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/workerV2")
@RestController
public class WorkerMedicalClaimV2RestController {

    @Autowired
    private WorkerMedicalClaimService workerMedicalClaimService;
    @Autowired
    private ResourceService resourceService;


    //1.列表接口----分页查询
    @RequestMapping(value = "/workerMedicalClaimList", method = RequestMethod.GET)
    public Object workerMedicalClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {


        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerMedicalClaim> workerMedicalClaimList = new ArrayList<WorkerMedicalClaim>();
        List<WorkerMedicalClaimDto> workerMedicalClaimDtoList = new ArrayList<WorkerMedicalClaimDto>();
        workerMedicalClaimList = workerMedicalClaimService.getWorkerMedicalClaimListByWorkerId(workerId, pageIndex, pageSize);
        for (WorkerMedicalClaim workerMedicalClaim : workerMedicalClaimList) {
            WorkerMedicalClaimDto workerMedicalClaimDto = new WorkerMedicalClaimDto();
            BeanUtils.copyProperties(workerMedicalClaim, workerMedicalClaimDto);
            workerMedicalClaimDto.setCreateTime(DateFormat.dateToString3(workerMedicalClaim.getCreateTime()));
//            List<Resource> resourceList = resourceService.selectArticle(UnionConstant.RESOURCE_ARTICLE_CODE_MEDICAL_CLAIM, workerMedicalClaimDto.getId() + "");
//            List<Map<String,Object>> resMapList = new ArrayList<Map<String,Object>>();
//            for(Resource resource:resourceList){
//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("resId",resource.getId());
//                map.put("resCode",resource.getResCode());
//                map.put("tag",resource.getTag());
//                map.put("url", ImagePathUtil.setFilePathStringServerName(resource.getUrl()));
//                resMapList.add(map);
//            }
//            workerMedicalClaimDto.setResMapList(resMapList);
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
        BeanUtils.copyProperties(workerMedicalClaim, workerMedicalClaimDto);
        workerMedicalClaimDto.setCreateTime(DateFormat.dateToString3(workerMedicalClaim.getCreateTime()));
        List<Map<String,Object>> resMapList = new ArrayList<Map<String,Object>>();
        if(!StringUtils.isEmpty(workerMedicalClaimDto.getResIds())){
            List<Resource> resourceList = resourceService.selectByIds(workerMedicalClaimDto.getResIds());
            for(Resource resource:resourceList){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("resId",resource.getId());
                map.put("resCode",resource.getResCode());
                map.put("tag",resource.getTag());
                map.put("url", ImagePathUtil.setFilePathStringServerName(resource.getUrl()));
                resMapList.add(map);
            }
        }
        workerMedicalClaimDto.setResMapList(resMapList);
        return Jsonp_data.success(workerMedicalClaimDto);
    }

    /*
    {
    "name": "123",
    "population": 5,
    "income": 10,
    "companyName": "12345679810",
    "telephone": "12345679810",

    "diseaseName": "12345679810",
    "selfPayingCase": "12345679810"
    }
     */
    //2.新增接口
    @RequestMapping(value = "/workerMedicalClaim", method = RequestMethod.POST)
    public Object createWorkerMedicalClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                           @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                           @RequestParam(name = "population", required = true, defaultValue = "") Integer population,
                                           @RequestParam(name = "income", required = true, defaultValue = "") Integer income,
                                           @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                           @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                           @RequestParam(name = "diseaseName", required = true, defaultValue = "") String diseaseName,
                                           @RequestParam(name = "selfPayingCase", required = true, defaultValue = "") String selfPayingCase,
                                           @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                           @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                           @RequestParam(name = "explainImgUrls", required = false) String explainImgUrls,
                                           @RequestParam(name = "ids", required = false) String ids
    ) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        WorkerMedicalClaim workerMedicalClaim = new WorkerMedicalClaim();
//        workerMedicalClaim.setExplainImgUrls(ImagePathUtil.getFileStringName(explainImgUrls));
        workerMedicalClaim.setWorkerId(workerId);
        workerMedicalClaim.setName(name);
        workerMedicalClaim.setPopulation(population);
        workerMedicalClaim.setIncome(income);
        workerMedicalClaim.setCompanyName(companyName);
        workerMedicalClaim.setTelephone(telephone);
        workerMedicalClaim.setDiseaseName(diseaseName);
        workerMedicalClaim.setSelfPayingCase(selfPayingCase);
        workerMedicalClaim.setBankName(bankName);
        workerMedicalClaim.setCardNumber(cardNumber);
        workerMedicalClaim.setResIds(ids);
        workerMedicalClaimService.saveWorkerMedicalClaim(workerMedicalClaim);
        if(null!=ids&&!"".equals(ids)) {
            resourceService.updateBatchByPrimaryKeySelective(ids, UnionConstant.RESOURCE_ARTICLE_CODE_MEDICAL_CLAIM, workerMedicalClaim.getId() + "");
        }
        return Jsonp.success();
    }


    //修改接口
    @RequestMapping(value = "/updateWorkerMedicalClaim", method = RequestMethod.POST)
    public Object updateWorkerMedicalClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                           @RequestParam(value = "id") Long id,
                                           @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                           @RequestParam(name = "population", required = true, defaultValue = "") Integer population,
                                           @RequestParam(name = "income", required = true, defaultValue = "") Integer income,
                                           @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                           @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                           @RequestParam(name = "diseaseName", required = true, defaultValue = "") String diseaseName,
                                           @RequestParam(name = "selfPayingCase", required = true, defaultValue = "") String selfPayingCase,
                                           @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                           @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                           @RequestParam(name = "explainImgUrls", required = false, defaultValue = "") String explainImgUrls,
                                           @RequestParam(name = "ids", required = false) String ids
    ) {

        WorkerMedicalClaim workerMedicalClaim = workerMedicalClaimService.findWorkerMedicalClaimById(id);
        resourceService.delete(workerMedicalClaim.getResIds());
        workerMedicalClaim.setStatus(1);
        workerMedicalClaim.setName(name);
        workerMedicalClaim.setPopulation(population);
        workerMedicalClaim.setIncome(income);
        workerMedicalClaim.setCompanyName(companyName);
        workerMedicalClaim.setTelephone(telephone);
        workerMedicalClaim.setDiseaseName(diseaseName);
        workerMedicalClaim.setSelfPayingCase(selfPayingCase);
        workerMedicalClaim.setBankName(bankName);
        workerMedicalClaim.setCardNumber(cardNumber);
//        workerMedicalClaim.setExplainImgUrls(ImagePathUtil.getFileStringName(explainImgUrls));
        workerMedicalClaim.setResIds(ids);
        workerMedicalClaimService.updateWorkerMedicalClaim(workerMedicalClaim);
        if(null!=ids&&!"".equals(ids)) {
            resourceService.updateBatchByPrimaryKeySelective(ids, UnionConstant.RESOURCE_ARTICLE_CODE_MEDICAL_CLAIM, workerMedicalClaim.getId() + "");
        }
        return Jsonp.success();
    }

    //已读接口
    @RequestMapping(value = "/workerMedicalClaimRead/{id}", method = RequestMethod.GET)
    public Object workerMedicalClaimRead(
            @PathVariable("id") Long id,
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {
        workerMedicalClaimService.updateIsReadedStatus(id);
        return Jsonp.success();
    }

}
