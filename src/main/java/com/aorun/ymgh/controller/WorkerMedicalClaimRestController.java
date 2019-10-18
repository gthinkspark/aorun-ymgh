package com.aorun.ymgh.controller;


import com.aorun.ymgh.dto.WorkerMedicalClaimDto;
import com.aorun.ymgh.model.WorkerMedicalClaim;
import com.aorun.ymgh.service.WorkerMedicalClaimService;
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
 * 医疗援助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerMedicalClaimRestController {

    @Autowired
    private WorkerMedicalClaimService workerMedicalClaimService;


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
            StringBuffer idCardUrlsList = new StringBuffer("");
            String explainImgUrls = workerMedicalClaim.getExplainImgUrls();
            if (explainImgUrls != null && !explainImgUrls.equals("")) {
                String _explainImgUrls[] = explainImgUrls.split(",");
                for (String explainImgUrl : _explainImgUrls) {
                    idCardUrlsList.append(ImagePropertiesConfig.MEDICAL_CLAIM_SERVER_PATH + explainImgUrl).append(",");
                }
            }
            workerMedicalClaimDto.setExplainImgUrls(idCardUrlsList.toString());
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
        StringBuffer idCardUrlsList = new StringBuffer("");
        String explainImgUrls = workerMedicalClaim.getExplainImgUrls();
        if (explainImgUrls != null && !explainImgUrls.equals("")) {
            String _explainImgUrls[] = explainImgUrls.split(",");
            for (String explainImgUrl : _explainImgUrls) {
                idCardUrlsList.append(ImagePropertiesConfig.MEDICAL_CLAIM_SERVER_PATH + explainImgUrl).append(",");
            }
        }
        workerMedicalClaimDto.setExplainImgUrls(idCardUrlsList.toString());
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
                                           @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles
    ) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        WorkerMedicalClaim workerMedicalClaim = new WorkerMedicalClaim();
        if (explainImgFiles != null && explainImgFiles.size() > 0) {
            try {
                StringBuffer explainImgUrls = new StringBuffer("");
                for (MultipartFile file : explainImgFiles) {
                    // Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    String uuid = UUID.randomUUID().toString();
                    String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                    String fileName = uuid + suffixName;
                    Path path = Paths.get(ImagePropertiesConfig.MEDICAL_CLAIM_PATH + fileName);
                    Files.write(path, bytes);
                    explainImgUrls.append(fileName).append(",");
                }
                workerMedicalClaim.setExplainImgUrls(explainImgUrls.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


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
        workerMedicalClaimService.saveWorkerMedicalClaim(workerMedicalClaim);
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
                                           @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles
    ) {

        WorkerMedicalClaim workerMedicalClaim = workerMedicalClaimService.findWorkerMedicalClaimById(id);
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
                    Path path = Paths.get(ImagePropertiesConfig.MEDICAL_CLAIM_PATH + fileName);
                    Files.write(path, bytes);
                    myexplainImgUrls.append(fileName).append(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        workerMedicalClaim.setExplainImgUrls(myexplainImgUrls.toString());

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
        workerMedicalClaimService.updateWorkerMedicalClaim(workerMedicalClaim);
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
