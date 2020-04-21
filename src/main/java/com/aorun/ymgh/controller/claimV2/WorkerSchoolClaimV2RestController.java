package com.aorun.ymgh.controller.claimV2;


import com.aorun.ymgh.dto.WorkerSchoolClaimDto;
import com.aorun.ymgh.model.Resource;
import com.aorun.ymgh.model.WorkerSchoolClaim;
import com.aorun.ymgh.service.ResourceService;
import com.aorun.ymgh.service.WorkerSchoolClaimService;
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
 * 助学救助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/workerV2")
@RestController
public class WorkerSchoolClaimV2RestController {

    @Autowired
    private WorkerSchoolClaimService workerSchoolClaimService;
    @Autowired
    private ResourceService resourceService;


    //1.列表接口----分页查询
    @RequestMapping(value = "/workerSchoolClaimList", method = RequestMethod.GET)
    public Object workerSchoolClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerSchoolClaim> workerSchoolClaimList = new ArrayList<WorkerSchoolClaim>();
        List<WorkerSchoolClaimDto> workerSchoolClaimDtoList = new ArrayList<WorkerSchoolClaimDto>();
        workerSchoolClaimList = workerSchoolClaimService.getWorkerSchoolClaimListByWorkerId(workerId, pageIndex, pageSize);
        for (WorkerSchoolClaim workerSchoolClaim : workerSchoolClaimList) {
            WorkerSchoolClaimDto workerSchoolClaimDto = new WorkerSchoolClaimDto();
            BeanUtils.copyProperties(workerSchoolClaim, workerSchoolClaimDto);
            workerSchoolClaimDto.setCreateTime(DateFormat.dateToString3(workerSchoolClaim.getCreateTime()));
//            List<Resource> resourceList = resourceService.selectArticle(UnionConstant.RESOURCE_ARTICLE_CODE_SCHOOL_CLAIM, workerSchoolClaimDto.getId() + "");
//            List<Map<String,Object>> resMapList = new ArrayList<Map<String,Object>>();
//            for(Resource resource:resourceList){
//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("resId",resource.getId());
//                map.put("resCode",resource.getResCode());
//                map.put("tag",resource.getTag());
//                map.put("url", ImagePathUtil.setFilePathStringServerName(resource.getUrl()));
//                resMapList.add(map);
//            }
//            workerSchoolClaimDto.setResMapList(resMapList);
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
        BeanUtils.copyProperties(workerSchoolClaim, workerSchoolClaimDto);
        workerSchoolClaimDto.setCreateTime(DateFormat.dateToString3(workerSchoolClaim.getCreateTime()));
        List<Map<String, Object>> resMapList = new ArrayList<Map<String, Object>>();
        if(!StringUtils.isEmpty(workerSchoolClaimDto.getResIds())) {
            List<Resource> resourceList = resourceService.selectByIds(workerSchoolClaimDto.getResIds());
            for (Resource resource : resourceList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("resId", resource.getId());
                map.put("resCode", resource.getResCode());
                map.put("tag", resource.getTag());
                map.put("url", ImagePathUtil.setFilePathStringServerName(resource.getUrl()));
                resMapList.add(map);
            }
        }
        workerSchoolClaimDto.setResMapList(resMapList);
        return Jsonp_data.success(workerSchoolClaimDto);
    }

    /*
    {
    "name": "123",
    "population": 5,
    "income": 10,
    "companyName": "12345679810",
    "telephone": "12345679810",
    "mainReason": "12345679810",

    "monthIncome": 10,
    "workerNumber": 10,
    "examScore": 10,

    "studentName": "12345679810",
    "relation": "12345679810",
    "enrollInfo": "12345679810",
    "enrollSchool": "12345679810",
    "selfPayingCase": "12345679810"
    }
     */
    //2.新增接口
    @RequestMapping(value = "/workerSchoolClaim", method = RequestMethod.POST)
    public Object createWorkerSchoolClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                          @RequestParam(name = "population", required = true, defaultValue = "") Integer population,
                                          @RequestParam(name = "income", required = true, defaultValue = "") Integer income,
                                          @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                          @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                          @RequestParam(name = "mainReason", required = true, defaultValue = "") String mainReason,
                                          @RequestParam(name = "monthIncome", required = true, defaultValue = "") Integer monthIncome,
                                          @RequestParam(name = "workerNumber", required = true, defaultValue = "") Integer workerNumber,
                                          @RequestParam(name = "examScore", required = true, defaultValue = "") Integer examScore,
                                          @RequestParam(name = "studentName", required = true, defaultValue = "") String studentName,
                                          @RequestParam(name = "relation", required = true, defaultValue = "") String relation,
                                          @RequestParam(name = "enrollInfo", required = true, defaultValue = "") String enrollInfo,
                                          @RequestParam(name = "enrollSchool", required = true, defaultValue = "") String enrollSchool,
                                          @RequestParam(name = "selfPayingCase", required = true, defaultValue = "") String selfPayingCase,
                                          @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                          @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                          @RequestParam(name = "explainImgUrls", required = false) String explainImgUrls,
                                          @RequestParam(name = "ids", required = false) String ids) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        WorkerSchoolClaim workerSchoolClaim = new WorkerSchoolClaim();

//        workerSchoolClaim.setExplainImgUrls(ImagePathUtil.getFileStringName(explainImgUrls));
        workerSchoolClaim.setWorkerId(workerId);
        workerSchoolClaim.setName(name);
        workerSchoolClaim.setPopulation(population);
        workerSchoolClaim.setIncome(income);
        workerSchoolClaim.setCompanyName(companyName);
        workerSchoolClaim.setTelephone(telephone);
        workerSchoolClaim.setMonthIncome(monthIncome);
        workerSchoolClaim.setWorkerNumber(workerNumber);
        workerSchoolClaim.setExamScore(examScore);
        workerSchoolClaim.setStudentName(studentName);
        workerSchoolClaim.setRelation(relation);
        workerSchoolClaim.setEnrollInfo(enrollInfo);
        workerSchoolClaim.setEnrollSchool(enrollSchool);
        workerSchoolClaim.setSelfPayingCase(selfPayingCase);
        workerSchoolClaim.setBankName(bankName);
        workerSchoolClaim.setCardNumber(cardNumber);
        workerSchoolClaim.setResIds(ids);
        workerSchoolClaimService.saveWorkerSchoolClaim(workerSchoolClaim);
        if(null!=ids&&!"".equals(ids)) {
            resourceService.updateBatchByPrimaryKeySelective(ids, UnionConstant.RESOURCE_ARTICLE_CODE_SCHOOL_CLAIM, workerSchoolClaim.getId() + "");
        }
        return Jsonp.success();
    }


    //修改接口
    @RequestMapping(value = "/updateWorkerSchoolClaim", method = RequestMethod.POST)
    public Object updateWorkerSchoolClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                          @RequestParam(value = "id") Long id,
                                          @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                          @RequestParam(name = "population", required = true, defaultValue = "") Integer population,
                                          @RequestParam(name = "income", required = true, defaultValue = "") Integer income,
                                          @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                          @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                          @RequestParam(name = "mainReason", required = true, defaultValue = "") String mainReason,
                                          @RequestParam(name = "monthIncome", required = true, defaultValue = "") Integer monthIncome,
                                          @RequestParam(name = "workerNumber", required = true, defaultValue = "") Integer workerNumber,
                                          @RequestParam(name = "examScore", required = true, defaultValue = "") Integer examScore,
                                          @RequestParam(name = "studentName", required = true, defaultValue = "") String studentName,
                                          @RequestParam(name = "relation", required = true, defaultValue = "") String relation,
                                          @RequestParam(name = "enrollInfo", required = true, defaultValue = "") String enrollInfo,
                                          @RequestParam(name = "enrollSchool", required = true, defaultValue = "") String enrollSchool,
                                          @RequestParam(name = "selfPayingCase", required = true, defaultValue = "") String selfPayingCase,
                                          @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                          @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                          @RequestParam(name = "explainImgUrls", required = false, defaultValue = "") String explainImgUrls,
                                          @RequestParam(name = "ids", required = false) String ids
                                          ) {
        WorkerSchoolClaim workerSchoolClaim = workerSchoolClaimService.findWorkerSchoolClaimById(id);
        resourceService.delete(workerSchoolClaim.getResIds());
//        workerSchoolClaim.setExplainImgUrls(ImagePathUtil.getFileStringName(explainImgUrls));
        workerSchoolClaim.setStatus(1);
        workerSchoolClaim.setName(name);
        workerSchoolClaim.setPopulation(population);
        workerSchoolClaim.setIncome(income);
        workerSchoolClaim.setCompanyName(companyName);
        workerSchoolClaim.setTelephone(telephone);
        workerSchoolClaim.setMonthIncome(monthIncome);
        workerSchoolClaim.setWorkerNumber(workerNumber);
        workerSchoolClaim.setExamScore(examScore);
        workerSchoolClaim.setStudentName(studentName);
        workerSchoolClaim.setRelation(relation);
        workerSchoolClaim.setEnrollInfo(enrollInfo);
        workerSchoolClaim.setEnrollSchool(enrollSchool);
        workerSchoolClaim.setSelfPayingCase(selfPayingCase);
        workerSchoolClaim.setBankName(bankName);
        workerSchoolClaim.setCardNumber(cardNumber);
        workerSchoolClaim.setResIds(ids);
        workerSchoolClaimService.updateWorkerSchoolClaim(workerSchoolClaim);
        if(null!=ids&&!"".equals(ids)) {
            resourceService.updateBatchByPrimaryKeySelective(ids, UnionConstant.RESOURCE_ARTICLE_CODE_SCHOOL_CLAIM, workerSchoolClaim.getId() + "");
        }
        return Jsonp.success();
    }

    //已读接口
    @RequestMapping(value = "/workerSchoolClaimRead/{id}", method = RequestMethod.GET)
    public Object workerSchoolClaimRead(@PathVariable("id") Long id,
                                        @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {
        workerSchoolClaimService.updateIsReadedStatus(id);
        return Jsonp.success();
    }

}
