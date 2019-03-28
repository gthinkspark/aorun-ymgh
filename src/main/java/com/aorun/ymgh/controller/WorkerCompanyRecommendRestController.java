package com.aorun.ymgh.controller;


import com.aorun.ymgh.model.WorkerCompanyRecommend;
import com.aorun.ymgh.service.WorkerCompanyRecommendService;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.biz.ImagePropertiesConfig;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 *
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerCompanyRecommendRestController {

    @Autowired
    private WorkerCompanyRecommendService workerCompanyRecommendService;


    //1.活动详情接口
    @RequestMapping(value = "/workerCompanyRecommend/{id}", method = RequestMethod.GET)
    public Object findOneWorkerLiveClaim(@PathVariable("id") Long id,
     @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {

        WorkerCompanyRecommend workerCompanyRecommend = workerCompanyRecommendService.findWorkerCompanyRecommendById(id);

        HashMap<String,Object> datamap = new HashMap<String,Object>();
        datamap.put("companyName",workerCompanyRecommend.getCompanyName());
        datamap.put("address", workerCompanyRecommend.getAddress());
        datamap.put("bannerUrl", ImagePropertiesConfig.COMPANY_RECOMMEND_SERVER_PATH+workerCompanyRecommend.getBannerUrl());
        datamap.put("beginTime", DateFormat.dateTimeToDateString(workerCompanyRecommend.getBeginTime()));
        datamap.put("endTime",DateFormat.dateTimeToDateString(workerCompanyRecommend.getEndTime()));
        //TODO:待完善连接：
        datamap.put("recommendDetailUrl","http://60.165.161.145:8085/shop/2/ybj.html");
        return Jsonp_data.success(datamap);
    }



}
