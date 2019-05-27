package com.aorun.ymgh.controller;


import com.aorun.ymgh.dto.WorkerCompanyRecommendDto;
import com.aorun.ymgh.model.WorkerCardApply;
import com.aorun.ymgh.model.WorkerCardWithBLOBs;
import com.aorun.ymgh.model.WorkerCompanyRecommend;
import com.aorun.ymgh.service.WorkerCardApplyService;
import com.aorun.ymgh.service.WorkerCardService;
import com.aorun.ymgh.service.WorkerCompanyRecommendService;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.biz.ImagePropertiesConfig;
import com.aorun.ymgh.util.biz.WorkerMemberUtil;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 我的福利模块接口
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerCardRestController {

    @Autowired
    private WorkerCardService workerCardService;

    @Autowired
    private WorkerCardApplyService workerCardApplyService;

    @Autowired
    private WorkerCompanyRecommendService workerCompanyRecommendService;


    //1.我的福利首页-页面接口
    @RequestMapping(value = "/workerCardPageHome", method = RequestMethod.GET)
    public Object workerLiveClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {


        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        //1.卡片详情
        WorkerCardWithBLOBs workerCard = workerCardService.findWorkerCardWithBLOBsById(1L);
        HashMap<String, Object> datamap = new HashMap<String, Object>();
        datamap.put("cardName", workerCard.getName());
        datamap.put("bannerUrl", ImagePropertiesConfig.CARD_SERVER_PATH + workerCard.getBannerUrl());
        datamap.put("simpleContent", workerCard.getSimpleContent());

        //2.是否申请过该卡片
        WorkerCardApply workerCardApply = workerCardApplyService.findWorkerCardApplyByWorkerIdAndCardId(workerId, 1L);
        if (workerCardApply != null) {
            datamap.put("status", workerCardApply.getStatus().toString());
            datamap.put("failReason", workerCardApply.getFailReason());
        } else {
            datamap.put("status", "0");// 0-未申请，1-审核中，2-审核失败，3-审核成功。
            datamap.put("failReason", "");
        }

        //3.商家推荐列表
        List<WorkerCompanyRecommend> workerCompanyRecommendList = new ArrayList<WorkerCompanyRecommend>();
        List<WorkerCompanyRecommendDto> workerCompanyRecommendDtoList = new ArrayList<WorkerCompanyRecommendDto>();
        workerCompanyRecommendList = workerCompanyRecommendService.getWorkerCompanyRecommendList(pageIndex, pageSize);
        for (WorkerCompanyRecommend workerCompanyRecommend : workerCompanyRecommendList) {
            WorkerCompanyRecommendDto workerCompanyRecommendDto = new WorkerCompanyRecommendDto();
            BeanUtils.copyProperties(workerCompanyRecommend, workerCompanyRecommendDto);
            workerCompanyRecommendDto.setBannerUrl(ImagePropertiesConfig.CARD_SERVER_PATH + workerCompanyRecommend.getBannerUrl());
            workerCompanyRecommendDto.setBeginTime(DateFormat.dateTimeToDateString(workerCompanyRecommend.getBeginTime()));
            workerCompanyRecommendDto.setEndTime(DateFormat.dateTimeToDateString(workerCompanyRecommend.getEndTime()));
            workerCompanyRecommendDtoList.add(workerCompanyRecommendDto);
        }
        datamap.put("workerCompanyRecommendDtoList", workerCompanyRecommendDtoList);
        return Jsonp_data.success(datamap);
    }

    //2.卡片详情接口
    @RequestMapping(value = "/workerCard", method = RequestMethod.GET)
    public Object findOneWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid) {
        WorkerCardWithBLOBs workerCard = workerCardService.findWorkerCardWithBLOBsById(1L);
        HashMap<String, Object> datamap = new HashMap<String, Object>();
        datamap.put("cardName", workerCard.getName());
        datamap.put("bannerUrl", ImagePropertiesConfig.CARD_SERVER_PATH + workerCard.getBannerUrl());
        datamap.put("simpleContent", workerCard.getSimpleContent());
        //datamap.put("cardDetailUrl","http://60.165.161.145:8085/shop/2/ybj.html");
        datamap.put("cardDetailUrl", workerCard.getFunctionUrl());
        return Jsonp_data.success(datamap);
    }


}
