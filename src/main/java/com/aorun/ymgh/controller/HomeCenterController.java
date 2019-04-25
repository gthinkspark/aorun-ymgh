package com.aorun.ymgh.controller;

import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.dto.MessageDto;
import com.aorun.ymgh.model.*;
import com.aorun.ymgh.service.*;
import com.aorun.ymgh.util.MessageUtil;
import com.aorun.ymgh.util.biz.WorkerMemberUtil;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 个人中心显示
 * @author: duxihu
 */
@RequestMapping("/worker")
@RestController
public class HomeCenterController {
    @Autowired
    private WorkerAdvisoryService workerAdvisoryService;

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageReadeService messageReadeService;

    @Autowired
    private WorkerCardApplyService workerCardApplyService;

    @Autowired
    private WorkerLiveClaimService workerLiveClaimService;

    @Autowired
    private WorkerMedicalClaimService workerMedicalClaimService;

    @Autowired
    private WorkerSchoolClaimService workerSchoolClaimService;

    @Autowired
    private WorkerTempClaimService workerTempClaimService;


    private final Integer MESSAGE_IS_READED=1;
    private final Integer MESSAGE_UN_READE=2;


    //个人中心页----红点提示接口
    @RequestMapping(value = "/homeCenterList", method = RequestMethod.GET)
    public Object homeCenterList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
    ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        UserDto  user = (UserDto) RedisCache.get(sid);
        WorkerCardApply workerCardApply = workerCardApplyService.findWorkerCardApplyByWorkerIdAndCardId(workerId,1L);
        Map<String,Object> datamap = new HashMap<>();
        if(workerCardApply!=null&&workerCardApply.getIsReaded()==2){//我的福利
            datamap.put("cardRead",MESSAGE_UN_READE);//2-未读
        }else {
            datamap.put("cardRead",MESSAGE_IS_READED);//1-已读
        }

        List<WorkerAdvisory> workerAdvisoryList =  workerAdvisoryService.getUnReadWorkerAdvisoryList(workerId);
        if(workerAdvisoryList!=null && workerAdvisoryList.size()>0){//我的维权
            datamap.put("advisoryRead",MESSAGE_UN_READE);
        }else{
            datamap.put("advisoryRead",MESSAGE_IS_READED);
        }

        //我的帮扶
        datamap.put("helpRead",MESSAGE_IS_READED);//我的帮扶 下面有4个模块

        List<WorkerLiveClaim> workerLiveClaimList = workerLiveClaimService.getUnReadList(workerId);
        if(workerLiveClaimList!=null && workerLiveClaimList.size()>0){//生活救助
            datamap.put("helpRead",MESSAGE_UN_READE);
        }

        List<WorkerMedicalClaim> workerMedicalClaimList = workerMedicalClaimService.getUnReadList(workerId);
        if(workerMedicalClaimList!=null && workerMedicalClaimList.size()>0){//理疗救助
            datamap.put("helpRead",MESSAGE_UN_READE);
        }

        List<WorkerSchoolClaim> workerSchoolClaimList = workerSchoolClaimService.getUnReadList(workerId);
        if(workerSchoolClaimList!=null && workerSchoolClaimList.size()>0){//生活救助
            datamap.put("helpRead",MESSAGE_UN_READE);
        }

        List<WorkerTempClaim> workerTempClaimList = workerTempClaimService.getUnReadList(workerId);
        if(workerTempClaimList!=null && workerTempClaimList.size()>0){//临时救助
            datamap.put("helpRead",MESSAGE_UN_READE);
        }


        //消息
        datamap.put("sysMessageRead",MESSAGE_IS_READED);
        datamap.put("unionMessageRead",MESSAGE_IS_READED);
        datamap.put("claimMessageRead",MESSAGE_IS_READED);
        //我的消息  系统通知
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("checkup", MessageUtil.MESSAGE_CHECK_OK);
        params.put("type", MessageUtil.MESSAGE_TYPE_SYS);
        params.put("statu", MessageUtil.MESSAGE_STATU_ISDEL_NO);
        params.put("start",0);
        params.put("limit",1);
        params.put("sort","create_time");
        params.put("dir","desc");
        List<Message> sysMessageList = messageService.findByMap(params);
        List<MessageDto> sysMessageDtoList = MessageUtil.setMessageReade(user, sysMessageList, messageReadeService);
        if(sysMessageDtoList.size()>0&&sysMessageDtoList.get(0).getIsReade()==MessageUtil.MESSAGE_READE_NO){
            datamap.put("sysMessageRead",MESSAGE_UN_READE);
        }
        //工会通知
        params.put("type", MessageUtil.MESSAGE_TYPE_UNION);
        List<Message> unionMessageList = messageService.findByMap(params);
        List<MessageDto> unionMessageDtoList = MessageUtil.setMessageReade(user, unionMessageList, messageReadeService);
        if(unionMessageDtoList.size()>0&&unionMessageDtoList.get(0).getIsReade()==MessageUtil.MESSAGE_READE_NO){
            datamap.put("unionMessageRead",MESSAGE_UN_READE);
        }
        //理赔通知
        params.put("type", MessageUtil.MESSAGE_TYPE_CLAIM);
        List<Message> cailmMessageList = messageService.findByMap(params);
        List<MessageDto> cailmMessageDtoList = MessageUtil.setMessageReade(user, cailmMessageList, messageReadeService);
        if(cailmMessageDtoList.size()>0&&cailmMessageDtoList.get(0).getIsReade()==MessageUtil.MESSAGE_READE_NO){
            datamap.put("claimMessageRead",MESSAGE_UN_READE);
        }
        return Jsonp_data.success(datamap);
    }

}
