package com.aorun.ymgh.controller;


import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.dto.WorkerApplyClaimDto;
import com.aorun.ymgh.model.WorkerApplyClaim;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.service.WorkerApplyClaimService;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.biz.ImagePropertiesConfig;
import com.aorun.ymgh.util.biz.UnionUtil;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 申请理赔
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerApplyClaimRestController {

    @Autowired
    private WorkerApplyClaimService workerApplyClaimService;



    /***
     * 申请理赔--请求短信验证码
     * @param phone   手机号
     * @param sourceCode  来源
     * @param macAddr  mac地址
     * @return
     */
    @RequestMapping(value = "/getRegisterSmsCode", method = RequestMethod.GET)
    public Object getRegisterSmsCode( @RequestParam(value="phone")String phone,
                                      @RequestParam(value="sourceCode",defaultValue="")String sourceCode,
                                      @RequestParam(value="macAddr",defaultValue="")String macAddr) {

        try {
            //1.验证自己的手机号能不能注册
            //2.验证短信是否发送超过次数
            //3.发送短信
            //4.返回客户端信息
//            String randomNum = AppCacheConstant.APP_SERVER_PRE + RandomNumUtil.getCharacterAndNumber(AppConstant.UNIQUE_CODE);
//            String smsCode = RandomNumUtil.getRandom(RandomNumUtil.NUM, RandomNumUtil.REGISTER_USER_LENGTH);
            String randomNum = UUID.randomUUID().toString();
            return  Jsonp_data.success(randomNum);

        } catch (Exception e) {
            return Jsonp.error();
        }
    }


        //1.列表接口----分页查询
        @RequestMapping(value = "/workerApplyClaimList", method = RequestMethod.GET)
        public Object workerLiveClaimList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name="pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name="pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
            ) {


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
        List<WorkerApplyClaim>   workerApplyClaimList = new ArrayList<WorkerApplyClaim>();
            List<Map<String,Object>> datamapList = new ArrayList<Map<String,Object>>();
            workerApplyClaimList = workerApplyClaimService.getWorkerApplyClaimListByWorkerId(workerId,pageIndex,pageSize);

        for(WorkerApplyClaim workerApplyClaim:workerApplyClaimList){
            Map<String,Object> datamap = new HashMap<>();
            datamap.put("id",workerApplyClaim.getId());// ID
            datamap.put("name",workerApplyClaim.getName());// 申请人姓名
            datamap.put("companyName",workerApplyClaim.getCompanyName());// 单 位 名 称
            datamap.put("planType",workerApplyClaim.getPlanType());//计 划 类 别
            datamap.put("createTime", DateFormat.dateTimeToDateString(workerApplyClaim.getCreateTime()));//申 请 日 期
            datamap.put("status",workerApplyClaim.getStatus());//状态  1-审核中，2-审核失败，3-审核成功。
            datamap.put("failReason",workerApplyClaim.getFailReason());//驳回原因
            datamapList.add(datamap);
        }
        return Jsonp_data.success(datamapList);
    }

    //3.详情接口
    @RequestMapping(value = "/workerApplyClaim/{id}", method = RequestMethod.GET)
    public Object findOneWorkerLiveClaim(@PathVariable("id") Long id) {

        WorkerApplyClaim workerApplyClaim = workerApplyClaimService.findWorkerApplyClaimById(id);
        WorkerApplyClaimDto workerApplyClaimDto = new WorkerApplyClaimDto();
        BeanUtils.copyProperties(workerApplyClaim,workerApplyClaimDto);

        workerApplyClaimDto.setBeginTime(DateFormat.dateTimeToDateString(workerApplyClaim.getBeginTime()));
        workerApplyClaimDto.setEndTime(DateFormat.dateTimeToDateString(workerApplyClaim.getEndTime()));
        workerApplyClaimDto.setCreateTime(DateFormat.dateTimeToDateString(workerApplyClaim.getCreateTime()));
        StringBuffer idCardUrlsList = new StringBuffer("");
        String explainImgUrls =  workerApplyClaim.getExplainImgUrls();
        if(explainImgUrls!=null&&!explainImgUrls.equals("")){
            String _explainImgUrls[] = explainImgUrls.split(",");
            for(String explainImgUrl:_explainImgUrls){
                idCardUrlsList.append(ImagePropertiesConfig.APPLY_CLAIM_SERVER_PATH+explainImgUrl).append(",");
            }
        }
        workerApplyClaimDto.setExplainImgUrls(idCardUrlsList.toString());

        return Jsonp_data.success(workerApplyClaimDto);
    }


    //2.新增接口
    @RequestMapping(value = "/workerApplyClaim", method = RequestMethod.POST)
    public Object createWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(value="smsCode",defaultValue="")String smsCode,
                                        @RequestParam(value="smsCodeBindingKey",defaultValue="")String smsCodeBindingKey,
                                        @RequestParam(name = "useTelephone", required = true, defaultValue = "") String useTelephone,
                                        @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "idCard", required = true, defaultValue = "") String idCard,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "planType", required = true, defaultValue = "") String planType,
                                        @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                        @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                        @RequestParam(name = "agencyName", required = true, defaultValue = "") String agencyName,
                                        @RequestParam(name = "hospitalName", required = true, defaultValue = "") String hospitalName,
                                        @RequestParam(name = "beginTime", required = true, defaultValue = "") String beginTime,
                                        @RequestParam(name = "endTime", required = true, defaultValue = "") String endTime,
                                        @RequestParam("explainImgFiles") List<MultipartFile> explainImgFiles
                                          ) {

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

        //        //TODO:判断验证码
//        /**获得DataCache里的验证码的值*/
//			String randomSmsCode = (String) MemachedCache.get(smsCodeBindingKey);
//			if (!smsCode.equals(randomSmsCode)) {
//				return Jsonp.error("验证码输入有误!");
//			}

        Long workerId = workerMember.getId();
        WorkerApplyClaim workerApplyClaim = new WorkerApplyClaim();
        workerApplyClaim.setWorkerId(workerId);
        if (explainImgFiles==null && explainImgFiles.size()<0) {
            return Jsonp.error("文件不能为空!");
        }
        try {
            StringBuffer explainImgUrls = new StringBuffer("");
            for(MultipartFile file:explainImgFiles){
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                String uuid = UUID.randomUUID().toString();
                String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                String fileName  = uuid+suffixName;
                Path path = Paths.get(ImagePropertiesConfig.APPLY_CLAIM_PATH + fileName);
                Files.write(path, bytes);
                explainImgUrls.append(fileName).append(",");
            }
            workerApplyClaim.setExplainImgUrls(explainImgUrls.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        workerApplyClaim.setUseTelephone(useTelephone);
        workerApplyClaim.setName(name);
        workerApplyClaim.setTelephone(telephone);
        workerApplyClaim.setIdCard(idCard);
        workerApplyClaim.setCompanyName(companyName);
        workerApplyClaim.setPlanType(planType);
        workerApplyClaim.setBankName(bankName);
        workerApplyClaim.setCardNumber(cardNumber);
        workerApplyClaim.setAgencyName(agencyName);
        workerApplyClaim.setHospitalName(hospitalName);
        workerApplyClaim.setBeginTime(DateFormat.stringToDateDay(beginTime));
        workerApplyClaim.setEndTime(DateFormat.stringToDateDay(endTime));
       workerApplyClaimService.saveWorkerApplyClaim(workerApplyClaim);
        return Jsonp.success();
    }


    // 修改接口
    @RequestMapping(value = "/updateWorkerApplyClaim", method = RequestMethod.POST)
    public Object updateWorkerLiveClaim(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(value="id")Long id,
                                        @RequestParam(value="smsCode",defaultValue="")String smsCode,
                                        @RequestParam(value="smsCodeBindingKey",defaultValue="")String smsCodeBindingKey,
                                        @RequestParam(name = "useTelephone", required = true, defaultValue = "") String useTelephone,
                                        @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "idCard", required = true, defaultValue = "") String idCard,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "planType", required = true, defaultValue = "") String planType,
                                        @RequestParam(name = "bankName", required = true, defaultValue = "") String bankName,
                                        @RequestParam(name = "cardNumber", required = true, defaultValue = "") String cardNumber,
                                        @RequestParam(name = "agencyName", required = true, defaultValue = "") String agencyName,
                                        @RequestParam(name = "hospitalName", required = true, defaultValue = "") String hospitalName,
                                        @RequestParam(name = "beginTime", required = true, defaultValue = "") String beginTime,
                                        @RequestParam(name = "endTime", required = true, defaultValue = "") String endTime,
                                        @RequestParam(name = "explainImgUrls", required = false, defaultValue = "") String explainImgUrls,
                                        @RequestParam(name = "explainImgFiles", required = false) List<MultipartFile> explainImgFiles
    ) {

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

//        //TODO:判断验证码
//        /**获得DataCache里的验证码的值*/
//			String randomSmsCode = (String) MemachedCache.get(smsCodeBindingKey);
//			if (!smsCode.equals(randomSmsCode)) {
//				return Jsonp.error("验证码输入有误!");
//			}


        WorkerApplyClaim workerApplyClaim = workerApplyClaimService.findWorkerApplyClaimById(id);
        if(workerApplyClaim!=null){

            StringBuffer myexplainImgUrls = new StringBuffer("");

            if(explainImgUrls!=null&&!explainImgUrls.equals("")){
                String[] explainImgUrl = explainImgUrls.split(",");
                for (String _explainImgUrl:explainImgUrl){
                    String subExplainImgUrl = _explainImgUrl.substring(_explainImgUrl.lastIndexOf("/")+1);
                    myexplainImgUrls.append(subExplainImgUrl).append(",");
                }
            }

            if(explainImgFiles!=null&&explainImgFiles.size()>0) {
                try {
                    for (MultipartFile file : explainImgFiles) {
                        // Get the file and save it somewhere
                        byte[] bytes = file.getBytes();
                        String uuid = UUID.randomUUID().toString();
                        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                        String fileName = uuid + suffixName;
                        Path path = Paths.get(ImagePropertiesConfig.APPLY_CLAIM_PATH + fileName);
                        Files.write(path, bytes);
                        myexplainImgUrls.append(fileName).append(",");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            workerApplyClaim.setExplainImgUrls(myexplainImgUrls.toString());
            workerApplyClaim.setUseTelephone(useTelephone);
            workerApplyClaim.setName(name);
            workerApplyClaim.setTelephone(telephone);
            workerApplyClaim.setIdCard(idCard);
            workerApplyClaim.setCompanyName(companyName);
            workerApplyClaim.setPlanType(planType);
            workerApplyClaim.setBankName(bankName);
            workerApplyClaim.setCardNumber(cardNumber);
            workerApplyClaim.setAgencyName(agencyName);
            workerApplyClaim.setHospitalName(hospitalName);
            workerApplyClaim.setBeginTime(DateFormat.stringToDateDay(beginTime));
            workerApplyClaim.setEndTime(DateFormat.stringToDateDay(endTime));
            workerApplyClaim.setStatus(1);
            workerApplyClaimService.updateWorkerApplyClaim(workerApplyClaim);
            return Jsonp.success();
        }else{
            return Jsonp.bussiness_tips_code("未找到该数据");
        }

    }


}
