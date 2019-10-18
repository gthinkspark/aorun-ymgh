package com.aorun.ymgh.controller;


import com.aorun.ymgh.dto.WorkerLegalAidDto;
import com.aorun.ymgh.model.WorkerLegalAid;
import com.aorun.ymgh.service.WorkerLegelAidService;
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
 * 法律援助
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerLegalAidRestController {

    @Autowired
    private WorkerLegelAidService workerLegelAidService;


    //1.列表接口----分页查询
    @RequestMapping(value = "/workerLegelAidList", method = RequestMethod.GET)
    public Object workerLiveClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {


        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerLegalAid> workerLegalAidList = new ArrayList<WorkerLegalAid>();
        List<WorkerLegalAidDto> workerLegalAidDtoList = new ArrayList<WorkerLegalAidDto>();
        workerLegalAidList = workerLegelAidService.getWorkerLegelAidListByWorkerId(workerId, pageIndex, pageSize);
        for (WorkerLegalAid workerLegalAid : workerLegalAidList) {
            WorkerLegalAidDto workerLegalAidDto = new WorkerLegalAidDto();
            BeanUtils.copyProperties(workerLegalAid, workerLegalAidDto);
            workerLegalAidDto.setCreateTime(DateFormat.dateToString3(workerLegalAid.getCreateTime()));
            StringBuffer idCardUrlsList = new StringBuffer("");
            String explainImgUrls = workerLegalAid.getExplainImgUrls();
            if (explainImgUrls != null && !explainImgUrls.equals("")) {
                String _explainImgUrls[] = explainImgUrls.split(",");
                for (String explainImgUrl : _explainImgUrls) {
                    idCardUrlsList.append(ImagePropertiesConfig.LEGAL_AID_SERVER_PATH + explainImgUrl).append(",");
                }
            }
            workerLegalAidDto.setExplainImgUrls(idCardUrlsList.toString());
            workerLegalAidDtoList.add(workerLegalAidDto);
        }
        return Jsonp_data.success(workerLegalAidDtoList);
    }

    //3.详情接口
    @RequestMapping(value = "/workerLegelAid/{id}", method = RequestMethod.GET)
    public Object findOneWorkerLiveClaim(@PathVariable("id") Long id,
                                         @RequestParam(name = "sid", required = true, defaultValue = "") String sid) {

        WorkerLegalAid workerLegalAid = workerLegelAidService.findWorkerLegelAidById(id);
        WorkerLegalAidDto workerLegalAidDto = new WorkerLegalAidDto();
        BeanUtils.copyProperties(workerLegalAid, workerLegalAidDto);
        workerLegalAidDto.setCreateTime(DateFormat.dateToString3(workerLegalAid.getCreateTime()));
        StringBuffer idCardUrlsList = new StringBuffer("");
        String explainImgUrls = workerLegalAid.getExplainImgUrls();
        if (explainImgUrls != null && !explainImgUrls.equals("")) {
            String _explainImgUrls[] = explainImgUrls.split(",");
            for (String explainImgUrl : _explainImgUrls) {
                idCardUrlsList.append(ImagePropertiesConfig.LEGAL_AID_SERVER_PATH + explainImgUrl).append(",");
            }
        }
        workerLegalAidDto.setExplainImgUrls(idCardUrlsList.toString());
        return Jsonp_data.success(workerLegalAidDto);
    }

    /*
    {
    "name": "fdsa",
    "companyName": "fdsa",
    "telephone": "12345679810",
	"caseReason": "12345679810",
    "replyItems": "12345679810"
}

     */
    //2.新增接口
    @RequestMapping(value = "/workerLegelAid", method = RequestMethod.POST)
    public Object createWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "caseReason", required = true, defaultValue = "") String caseReason,
                                        @RequestParam(name = "replyItems", required = true, defaultValue = "") String replyItems,
                                        @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        WorkerLegalAid workerLegalAid = new WorkerLegalAid();

        if (explainImgFiles != null && explainImgFiles.size() > 0) {
            try {
                StringBuffer explainImgUrls = new StringBuffer("");
                for (MultipartFile file : explainImgFiles) {
                    // Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    String uuid = UUID.randomUUID().toString();
                    String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                    String fileName = uuid + suffixName;
                    Path path = Paths.get(ImagePropertiesConfig.LEGAL_AID_PATH + fileName);
                    Files.write(path, bytes);
                    explainImgUrls.append(fileName).append(",");
                }
                workerLegalAid.setExplainImgUrls(explainImgUrls.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        workerLegalAid.setWorkerId(workerId);
        workerLegalAid.setName(name);
        workerLegalAid.setCompanyName(companyName);
        workerLegalAid.setTelephone(telephone);
        workerLegalAid.setCaseReason(caseReason);
        workerLegalAid.setReplyItems(replyItems);
        workerLegelAidService.saveWorkerLegelAid(workerLegalAid);
        return Jsonp.success();
    }


    //修改接口
    @RequestMapping(value = "/updateWorkerLegelAid", method = RequestMethod.POST)
    public Object updateWorkerLegelAid(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                       @RequestParam(value = "id") Long id,
                                       @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                       @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                       @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                       @RequestParam(name = "caseReason", required = true, defaultValue = "") String caseReason,
                                       @RequestParam(name = "replyItems", required = true, defaultValue = "") String replyItems,
                                       @RequestParam(name = "explainImgUrls", required = false, defaultValue = "") String explainImgUrls,
                                       @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles
                                       ) {
        WorkerLegalAid workerLegalAid = workerLegelAidService.findWorkerLegelAidById(id);
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
                    Path path = Paths.get(ImagePropertiesConfig.LEGAL_AID_PATH + fileName);
                    Files.write(path, bytes);
                    myexplainImgUrls.append(fileName).append(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        workerLegalAid.setExplainImgUrls(myexplainImgUrls.toString());




        workerLegalAid.setStatus(1);
        workerLegalAid.setName(name);
        workerLegalAid.setCompanyName(companyName);
        workerLegalAid.setTelephone(telephone);
        workerLegalAid.setCaseReason(caseReason);
        workerLegalAid.setReplyItems(replyItems);

        workerLegelAidService.updateWorkerLegelAid(workerLegalAid);
        return Jsonp.success();
    }


}
