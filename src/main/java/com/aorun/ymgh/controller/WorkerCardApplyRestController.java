package com.aorun.ymgh.controller;


import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.dto.WorkerCardApplyDto;
import com.aorun.ymgh.model.WorkerCardApply;
import com.aorun.ymgh.model.WorkerCardWithBLOBs;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.service.WorkerCardApplyService;
import com.aorun.ymgh.service.WorkerCardService;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.biz.ImagePropertiesConfig;
import com.aorun.ymgh.util.biz.UnionUtil;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 *申请办卡
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerCardApplyRestController {

    @Autowired
    private WorkerCardApplyService workerCardApplyService;

    @Autowired
    private WorkerCardService workerCardService;


    //1.申请办卡详情接口
    @RequestMapping(value = "/workerCardApply_detail", method = RequestMethod.GET)
    public Object findOneWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid) {
        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
            if (CheckObjectIsNull.isNull(user)) {
                return Jsonp.noLoginError("请先登录或重新登录");
            }
            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
            if (CheckObjectIsNull.isNull(workerMember)) {
                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
            }
        } else {
            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
        }

        Long workerId = workerMember.getId();

        WorkerCardApply workerCardApply = workerCardApplyService.findWorkerCardApplyByWorkerIdAndCardId(workerId,1L);
        WorkerCardApplyDto workerCardApplyDto = new WorkerCardApplyDto();
        BeanUtils.copyProperties(workerCardApply,workerCardApplyDto);


        StringBuffer idCardUrlsList = new StringBuffer("");
        String idCardUrls =  workerCardApply.getIdCardUrls();
        if(idCardUrls!=null&&!idCardUrls.equals("")){
            String _idCardUrls[] = idCardUrls.split(",");
            for(String idCardUrl:_idCardUrls){
                idCardUrlsList.append(ImagePropertiesConfig.APPLY_CARD_SERVER_PATH+idCardUrl).append(",");
            }
        }
        workerCardApplyDto.setIdCardUrls(idCardUrlsList.toString());
        return Jsonp_data.success(workerCardApplyDto);
    }


    //1.申请办卡-修改接口
        @RequestMapping(value = "/workerCardApply", method = RequestMethod.PUT)
        public Object updateWorkerCardApply(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                @RequestParam(name = "id", required = true) Long id,
                @RequestParam(name = "applyName", required = true, defaultValue = "") String applyName,
                @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                @RequestParam(name = "idCardNumber", required = true, defaultValue = "") String idCardNumber,
                @RequestParam(name = "workerName", required = true, defaultValue = "") String workerName,
                @RequestParam("idCardFiles") List<MultipartFile> idCardFiles) {
        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
            if (CheckObjectIsNull.isNull(user)) {
                return Jsonp.noLoginError("请先登录或重新登录");
            }
            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
            if (CheckObjectIsNull.isNull(workerMember)) {
                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
            }
        } else {
            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
        }


        Long workerId = workerMember.getId();
        WorkerCardApply workerCardApply = workerCardApplyService.findWorkerCardApplyById(id);
        if(workerCardApply!=null){
            //      workerCardApply.setWorkerId(workerId);
////    workerCardApply.setWorkerCardId(1L);
            workerCardApply.setApplyName(applyName);
            workerCardApply.setTelephone(telephone);
            workerCardApply.setCompanyName(companyName);
            workerCardApply.setIdCardNumber(idCardNumber);
            workerCardApply.setWorkerName(workerName);

            if (idCardFiles==null && idCardFiles.size()<0) {
                return Jsonp.error("文件不能为空!");
            }
            try {
                StringBuffer idCardUrls = new StringBuffer("");
                for(MultipartFile file:idCardFiles){
                    // Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    String uuid = UUID.randomUUID().toString();
                    String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                    String fileName  = uuid+suffixName;
                    Path path = Paths.get(ImagePropertiesConfig.APPLY_CARD_PATH + fileName);
                    Files.write(path, bytes);
                    idCardUrls.append(fileName).append(",");
                }
                workerCardApply.setIdCardUrls(idCardUrls.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }



            workerCardApplyService.updateWorkerCardApply(workerCardApply);
            return Jsonp.success();
        }else{
            return Jsonp.bussiness_tips_code("暂无此申请信息");
        }

    }



    //1.申请办卡接口
    @RequestMapping(value = "/workerCardApply", method = RequestMethod.POST)
    public Object createWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(name = "applyName", required = true, defaultValue = "") String applyName,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "idCardNumber", required = true, defaultValue = "") String idCardNumber,
                                        @RequestParam(name = "workerName", required = true, defaultValue = "") String workerName,
                                        @RequestParam("idCardFiles") List<MultipartFile> idCardFiles) {

        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
            if (CheckObjectIsNull.isNull(user)) {
                return Jsonp.noLoginError("请先登录或重新登录");
            }
            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
            if (CheckObjectIsNull.isNull(workerMember)) {
                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
            }
        } else {
            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
        }

        //先判断有无申请
        Long workerId = workerMember.getId();
        WorkerCardApply _workerCardApply = workerCardApplyService.findWorkerCardApplyByWorkerIdAndCardId(workerId,1L);
         if(_workerCardApply!=null){
             return Jsonp.bussiness_tips_code("已申请过此卡");
         }else{
             WorkerCardApply workerCardApply = new WorkerCardApply();
             workerCardApply.setWorkerId(workerId);
             workerCardApply.setWorkerCardId(1L);
             workerCardApply.setApplyName(applyName);
             workerCardApply.setTelephone(telephone);
             workerCardApply.setCompanyName(companyName);
             workerCardApply.setIdCardNumber(idCardNumber);
             workerCardApply.setWorkerName(workerName);

             if (idCardFiles==null && idCardFiles.size()<0) {
                 return Jsonp.error("文件不能为空!");
             }
             try {
                 StringBuffer idCardUrls = new StringBuffer("");
                 for(MultipartFile file:idCardFiles){
                     // Get the file and save it somewhere
                     byte[] bytes = file.getBytes();
                     String uuid = UUID.randomUUID().toString();
                     String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                     String fileName  = uuid+suffixName;
                     Path path = Paths.get(ImagePropertiesConfig.APPLY_CARD_PATH + fileName);
                     Files.write(path, bytes);
                     idCardUrls.append(fileName).append(",");
                 }
                 workerCardApply.setIdCardUrls(idCardUrls.toString());

             } catch (IOException e) {
                 e.printStackTrace();
             }

             workerCardApplyService.saveWorkerCardApply(workerCardApply);
             return Jsonp.success();
         }

    }


    //1.个人中心---普惠卡详情接口
    @RequestMapping(value = "/workerCardCenterApply_detail", method = RequestMethod.GET)
    public Object workerCardCenterApply_detail(@RequestParam(name = "sid", required = true, defaultValue = "") String sid) {
        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
            if (CheckObjectIsNull.isNull(user)) {
                return Jsonp.noLoginError("请先登录或重新登录");
            }
            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
            if (CheckObjectIsNull.isNull(workerMember)) {
                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
            }
        } else {
            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
        }

        Long workerId = workerMember.getId();
        WorkerCardApply workerCardApply = workerCardApplyService.findWorkerCardApplyByWorkerIdAndCardId(workerId,1L);


        WorkerCardWithBLOBs workerCard = workerCardService.findWorkerCardWithBLOBsById(1L);
        HashMap<String,Object> datamap = new HashMap<String,Object>();
        datamap.put("cardName",workerCard.getName());
        datamap.put("bannerUrl",ImagePropertiesConfig.CARD_SERVER_PATH+workerCard.getBannerUrl());
        datamap.put("simpleContent",workerCard.getSimpleContent());
        datamap.put("cardDetailUrl",workerCard.getFunctionUrl());
        datamap.put("applyConditionUrl",workerCard.getApplyConditionUrl());
        datamap.put("serviceConditionUrl",workerCard.getServiceConditionUrl());
        datamap.put("status",workerCardApply.getStatus());
        datamap.put("failReason",workerCardApply.getFailReason());

        return Jsonp_data.success(datamap);
    }


}
