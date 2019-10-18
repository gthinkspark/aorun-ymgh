package com.aorun.ymgh.controller;


import com.aorun.ymgh.dto.WorkerTempClaimDto;
import com.aorun.ymgh.model.WorkerTempClaim;
import com.aorun.ymgh.service.WorkerTempClaimService;
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
 * 临时救助
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
            StringBuffer idCardUrlsList = new StringBuffer("");
            String explainImgUrls = workerTempClaim.getExplainImgUrls();
            if (explainImgUrls != null && !explainImgUrls.equals("")) {
                String _explainImgUrls[] = explainImgUrls.split(",");
                for (String explainImgUrl : _explainImgUrls) {
                    idCardUrlsList.append(ImagePropertiesConfig.TEMP_CLAIM_SERVER_PATH + explainImgUrl).append(",");
                }
            }
            workerTempClaimDto.setExplainImgUrls(idCardUrlsList.toString());
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
        StringBuffer idCardUrlsList = new StringBuffer("");
        String explainImgUrls = workerTempClaim.getExplainImgUrls();
        if (explainImgUrls != null && !explainImgUrls.equals("")) {
            String _explainImgUrls[] = explainImgUrls.split(",");
            for (String explainImgUrl : _explainImgUrls) {
                idCardUrlsList.append(ImagePropertiesConfig.TEMP_CLAIM_SERVER_PATH + explainImgUrl).append(",");
            }
        }
        workerTempClaimDto.setExplainImgUrls(idCardUrlsList.toString());
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
                                        @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        WorkerTempClaim workerTempClaim = new WorkerTempClaim();
        if (explainImgFiles != null && explainImgFiles.size() > 0) {
            try {
                StringBuffer explainImgUrls = new StringBuffer("");
                for (MultipartFile file : explainImgFiles) {
                    // Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    String uuid = UUID.randomUUID().toString();
                    String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                    String fileName = uuid + suffixName;
                    Path path = Paths.get(ImagePropertiesConfig.TEMP_CLAIM_PATH + fileName);
                    Files.write(path, bytes);
                    explainImgUrls.append(fileName).append(",");
                }
                workerTempClaim.setExplainImgUrls(explainImgUrls.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
                                        @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles
                                        ) {
        WorkerTempClaim workerTempClaim = workerTempClaimService.findWorkerTempClaimById(id);

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
                    Path path = Paths.get(ImagePropertiesConfig.TEMP_CLAIM_PATH + fileName);
                    Files.write(path, bytes);
                    myexplainImgUrls.append(fileName).append(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        workerTempClaim.setExplainImgUrls(myexplainImgUrls.toString());


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
