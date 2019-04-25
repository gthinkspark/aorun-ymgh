package com.aorun.ymgh.controller;

import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.dto.MessageDto;
import com.aorun.ymgh.model.Message;
import com.aorun.ymgh.model.WorkerAdvisory;
import com.aorun.ymgh.model.WorkerCardApply;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.service.MessageReadeService;
import com.aorun.ymgh.service.MessageService;
import com.aorun.ymgh.service.WorkerAdvisoryService;
import com.aorun.ymgh.service.WorkerCardApplyService;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.MessageUtil;
import com.aorun.ymgh.util.biz.UnionUtil;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    private final Integer MESSAGE_IS_READED=1;
    private final Integer MESSAGE_UN_READE=2;


    //个人中心页----红点提示接口
    @RequestMapping(value = "/homeCenterList", method = RequestMethod.GET)
    public Object homeCenterList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
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
        WorkerCardApply workerCardApply = workerCardApplyService.findWorkerCardApplyByWorkerIdAndCardId(workerId,1L);
        Map<String,Object> datamap = new HashMap<>();
        if(workerCardApply!=null&&workerCardApply.getIsReaded()==1){//我的福利
            datamap.put("cardRead",MESSAGE_IS_READED);
        }else {
            datamap.put("cardRead",MESSAGE_UN_READE);
        }

        List<WorkerAdvisory> workerAdvisoryList =  workerAdvisoryService.getUnReadWorkerAdvisoryList(workerId);
        if(workerAdvisoryList!=null && workerAdvisoryList.size()>0){//我的维权
            datamap.put("advisoryRead",MESSAGE_UN_READE);
        }else{
            datamap.put("advisoryRead",MESSAGE_IS_READED);
        }

        //TODO:我的帮扶未读--状态判断
        datamap.put("helpRead",MESSAGE_IS_READED);//我的帮扶


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
        params.put("memberId",user.getMemberId());
        params.put("type", MessageUtil.MESSAGE_TYPE_CLAIM);
        List<Message> cailmMessageList = messageService.findByMap(params);
        List<MessageDto> cailmMessageDtoList = MessageUtil.setMessageReade(user, cailmMessageList, messageReadeService);
        if(cailmMessageDtoList.size()>0&&cailmMessageDtoList.get(0).getIsReade()==MessageUtil.MESSAGE_READE_NO){
            datamap.put("claimMessageRead",MESSAGE_UN_READE);
        }
        return Jsonp_data.success(datamap);
    }

}
