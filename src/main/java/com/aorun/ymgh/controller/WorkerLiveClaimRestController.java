package com.aorun.ymgh.controller;


import com.aorun.ymgh.dto.WorkerLiveClaimDto;
import com.aorun.ymgh.model.WorkerLiveClaim;
import com.aorun.ymgh.service.WorkerLiveClaimService;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.biz.ImagePropertiesConfig;
import com.aorun.ymgh.util.biz.WorkerMemberUtil;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 生活援助
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
            StringBuffer idCardUrlsList = new StringBuffer("");
            String explainImgUrls = workerLiveClaim.getExplainImgUrls();
            if (explainImgUrls != null && !explainImgUrls.equals("")) {
                String _explainImgUrls[] = explainImgUrls.split(",");
                for (String explainImgUrl : _explainImgUrls) {
                    idCardUrlsList.append(ImagePropertiesConfig.LIVE_CLAIM_SERVER_PATH + explainImgUrl).append(",");
                }
            }
            workerLiveClaimDto.setExplainImgUrls(idCardUrlsList.toString());
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
        StringBuffer idCardUrlsList = new StringBuffer("");
        String explainImgUrls = workerLiveClaim.getExplainImgUrls();
        if (explainImgUrls != null && !explainImgUrls.equals("")) {
            String _explainImgUrls[] = explainImgUrls.split(",");
            for (String explainImgUrl : _explainImgUrls) {
                idCardUrlsList.append(ImagePropertiesConfig.LIVE_CLAIM_SERVER_PATH + explainImgUrl).append(",");
            }
        }
        workerLiveClaimDto.setExplainImgUrls(idCardUrlsList.toString());
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
                                        @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles
    ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        WorkerLiveClaim workerLiveClaim = new WorkerLiveClaim();

        if (explainImgFiles != null && explainImgFiles.size() > 0) {
            try {
                StringBuffer explainImgUrls = new StringBuffer("");
                for (MultipartFile file : explainImgFiles) {
                    // Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    String uuid = UUID.randomUUID().toString();
                    String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                    String fileName = uuid + suffixName;
                    Path path = Paths.get(ImagePropertiesConfig.LIVE_CLAIM_PATH + fileName);
                    Files.write(path, bytes);
                    explainImgUrls.append(fileName).append(",");
                }
                workerLiveClaim.setExplainImgUrls(explainImgUrls.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        workerLiveClaim.setWorkerId(workerId);
        workerLiveClaim.setName(name);
        workerLiveClaim.setPopulation(population);
        workerLiveClaim.setIncome(income);
        workerLiveClaim.setCompanyName(companyName);
        workerLiveClaim.setTelephone(telephone);
        workerLiveClaim.setMainReason(mainReason);
        workerLiveClaim.setBankName(bankName);
        workerLiveClaim.setCardNumber(cardNumber);
        workerLiveClaimService.saveWorkerLiveClaim(workerLiveClaim);
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
                                        @RequestParam(name = "explainImgUrls", required = false, defaultValue = "") String explainImgUrls,
                                        @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles
    ) {

        WorkerLiveClaim workerLiveClaim = workerLiveClaimService.findWorkerLiveClaimById(id);

        StringBuffer myexplainImgUrls = new StringBuffer("");

        if (explainImgUrls != null && !explainImgUrls.equals("")) {
            String[] explainImgUrl = explainImgUrls.split(",");
            for (String _explainImgUrl : explainImgUrl) {
                String subExplainImgUrl = _explainImgUrl.substring(_explainImgUrl.lastIndexOf("/") + 1);
                myexplainImgUrls.append(subExplainImgUrl).append(",");
            }
        }

        if (explainImgFiles != null && explainImgFiles.size() > 0) {
            try {
                for (MultipartFile file : explainImgFiles) {
                    // Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    String uuid = UUID.randomUUID().toString();
                    String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                    String fileName = uuid + suffixName;
                    Path path = Paths.get(ImagePropertiesConfig.LIVE_CLAIM_PATH + fileName);
                    Files.write(path, bytes);
                    myexplainImgUrls.append(fileName).append(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        workerLiveClaim.setExplainImgUrls(myexplainImgUrls.toString());


        workerLiveClaim.setStatus(1);
        workerLiveClaim.setName(name);
        workerLiveClaim.setPopulation(population);
        workerLiveClaim.setIncome(income);
        workerLiveClaim.setCompanyName(companyName);
        workerLiveClaim.setTelephone(telephone);
        workerLiveClaim.setMainReason(mainReason);
        workerLiveClaim.setBankName(bankName);
        workerLiveClaim.setCardNumber(cardNumber);
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
