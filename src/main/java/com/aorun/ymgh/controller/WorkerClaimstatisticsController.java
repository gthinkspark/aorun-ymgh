package com.aorun.ymgh.controller;


import com.aorun.ymgh.model.*;
import com.aorun.ymgh.service.*;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 * 救助统计
 * * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/statistics")
@RestController
public class WorkerClaimstatisticsController {

    @Autowired
    private WorkerTempClaimService workerTempClaimService;

    @Autowired
    private WorkerLiveClaimService workerLiveClaimService;


    @Autowired
    private WorkerMedicalClaimService workerMedicalClaimService;

    @Autowired
    private WorkerSchoolClaimService workerSchoolClaimService;


    @Autowired
    private WorkerApplyClaimService workerApplyClaimService;



    //1.列表接口----分页查询
    @RequestMapping(value = "/claimStatistics", method = RequestMethod.GET)
    public Object workerTempClaimList(
    ) {

        HashMap<String, Object> datamap = new HashMap<String, Object>();

        List<WorkerTempClaim> workerTempClaimList =  workerTempClaimService.getAllList();
        int workerTempClaimNum = workerTempClaimList.size();
        List<WorkerLiveClaim> workerLiveClaimList = workerLiveClaimService.getAllList();
        int workerLiveClaimNum = workerLiveClaimList.size();
        List<WorkerMedicalClaim> workerMedicalClaimList = workerMedicalClaimService.getAllList();
        int workerMedicalClaimNum = workerMedicalClaimList.size();
        List<WorkerSchoolClaim> workerSchoolClaimList = workerSchoolClaimService.getAllList();
        int workerSchoolClaimNum = workerSchoolClaimList.size();
        List<WorkerApplyClaim> workerApplyClaimList = workerApplyClaimService.getAllList();
        int workerApplyClaimNum = workerApplyClaimList.size();

        int totalNum = workerTempClaimNum+workerLiveClaimNum+workerMedicalClaimNum+workerSchoolClaimNum+workerApplyClaimNum;


        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数


        datamap.put("tempClaimName", "临时救助");
        datamap.put("tempClaimNum", df.format((float)100*workerTempClaimNum/totalNum));



        datamap.put("liveClaimName", "生活救助");
        datamap.put("liveClaimNum", df.format((float)100*workerLiveClaimNum/totalNum));
        datamap.put("medicalClaimName", "医疗救助");
        datamap.put("medicalClaimNum", df.format((float)100*workerMedicalClaimNum/totalNum));
        datamap.put("schoolClaimName", "助学救助");
        datamap.put("schoolClaimNum", df.format((float)100*workerSchoolClaimNum/totalNum));
        datamap.put("applyClaimName", "申请理赔");
        datamap.put("applyClaimNum", df.format((float)100*workerApplyClaimNum/totalNum));
        return Jsonp_data.success(datamap);
    }



}
