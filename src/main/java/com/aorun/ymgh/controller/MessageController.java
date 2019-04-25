package com.aorun.ymgh.controller;

import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.dto.MessageDto;
import com.aorun.ymgh.model.Message;
import com.aorun.ymgh.model.MessageReade;
import com.aorun.ymgh.service.MessageReadeService;
import com.aorun.ymgh.service.MessageService;
import com.aorun.ymgh.util.MessageUtil;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName: MessageController
 * @Description: TODO
 * @author: lg
 * @date: 2019/3/28 15:13
 */
@RequestMapping("/message")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageReadeService messageReadeService;



    //1.列表接口----分页查询
    @RequestMapping(value = "/messageList/{type}", method = RequestMethod.GET)
    public Object messageList(
            @PathVariable("type") Integer type,
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name="pageIndex", required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(name="pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {
        UserDto  user = (UserDto) RedisCache.get(sid);
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("checkup", MessageUtil.MESSAGE_CHECK_OK);
        params.put("statu", MessageUtil.MESSAGE_STATU_ISDEL_NO);
        params.put("start",(pageIndex-1)*pageSize);
        params.put("limit",pageIndex*pageSize);
        params.put("type",type);
        params.put("sort","create_time");
        params.put("dir","desc");
        if(type!= MessageUtil.MESSAGE_TYPE_SYS){
            params.put("memberId",user.getMemberId());
        }
        List<Message> messageList = messageService.findByMap(params);
        List<MessageDto> messageDtoList = MessageUtil.setMessageReade(user, messageList,messageReadeService);
        //todo:将是否已读标记存入缓存
        resultMap.put("messageList", messageDtoList);
        return Jsonp_data.success(resultMap);
    }
    //2.消息首页接口----分页查询
    @RequestMapping(value = "/messageHomeList", method = RequestMethod.GET)
    public Object messageHomeList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
    ) {

        UserDto  user = (UserDto) RedisCache.get(sid);
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("checkup", MessageUtil.MESSAGE_CHECK_OK);
        params.put("type", MessageUtil.MESSAGE_TYPE_SYS);
        params.put("statu", MessageUtil.MESSAGE_STATU_ISDEL_NO);
        params.put("start",0);
        params.put("limit",1);
        params.put("sort","create_time");
        params.put("dir","desc");
        /*系统通知*/
        Map<String,Object> sysMessageMap = new HashMap<>();
        List<Message> sysMessageList = messageService.findByMap(params);
        sysMessageMap.put("name","系统通知");
        sysMessageMap.put("icon","http://mov.91catv.com/img/userfiles//images/news/defIcon/def.png");
        List<MessageDto> sysMessageDtoList =  MessageUtil.setMessageReade(user, sysMessageList,messageReadeService);
        sysMessageMap.put("sysMessageList",sysMessageDtoList);
        Map<String,Object> unionMessageMap = new HashMap<>();
        Map<String,Object> calimMessageMap = new HashMap<>();
        List<Message> unionMessageList = new ArrayList<Message>();
        List<Message> claimMessageList = new ArrayList<Message>();
        if(null!=user){
            params.put("memberId",user.getMemberId());
            params.put("type", MessageUtil.MESSAGE_TYPE_UNION);
            unionMessageList = messageService.findByMap(params);
            params.put("type", MessageUtil.MESSAGE_TYPE_CLAIM);
            claimMessageList = messageService.findByMap(params);
        }
        /*工会通知*/
        List<MessageDto> unionMessageDtoList = MessageUtil.setMessageReade(user, unionMessageList,messageReadeService);
        unionMessageMap.put("unionMessageList",unionMessageDtoList);
        unionMessageMap.put("name","工会通知");
        unionMessageMap.put("icon","http://mov.91catv.com/img/userfiles//images/news/defIcon/def.png");
        /*理赔通知*/
        List<MessageDto> calimMessageDtoList = MessageUtil.setMessageReade(user, claimMessageList,messageReadeService);
        calimMessageMap.put("claimMessageList",calimMessageDtoList);
        calimMessageMap.put("name","理赔通知");
        calimMessageMap.put("icon","http://mov.91catv.com/img/userfiles//images/news/defIcon/def.png");
        resultMap.put("sysMessage",sysMessageMap);
        resultMap.put("unionMessage",unionMessageMap);
        resultMap.put("claimMessage",calimMessageMap);
        return Jsonp_data.success(resultMap);
    }


    //3.单条消息已读接口
    @RequestMapping(value = "/messageReade", method = RequestMethod.POST)
    public Object messageReade(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                 @RequestParam(name = "messageId", required = true, defaultValue = "") Long messageId,
                                 @RequestParam(name = "source", required = true, defaultValue = "") Integer source,
                                 @RequestParam(name = "deviceCode", required = true, defaultValue = "") String deviceCode) {

        UserDto  user = (UserDto) RedisCache.get(sid);

        MessageReade messageReade = new MessageReade();
        messageReade.setMemberId(user.getMemberId());
        messageReade.setMessageId(messageId);
        messageReade.setSource(source);
        messageReade.setDeviceCode(deviceCode);
        messageReade.setCreateTime(new Date());
        messageReade.setIsReade(MessageUtil.MESSAGE_READE_OK);             //默认1是已读,此表只记录已读信息
        messageReadeService.add(messageReade);
        return Jsonp.success();
    }

//    //4.类型消息已读接口
//    @RequestMapping(value = "/messageReade", method = RequestMethod.POST)
//    public Object messageReade(  @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
//                                 @RequestParam(name = "messageType", required = true, defaultValue = "") Integer messageType,
//                                 @RequestParam(name = "source", required = true, defaultValue = "") Integer source,
//                                 @RequestParam(name = "deviceCode", required = true, defaultValue = "") String deviceCode) {
//        UserDto user = null;
//        WorkerMember workerMember = null;
//        if (!StringUtils.isEmpty(sid)) {
//            user = (UserDto) RedisCache.get(sid);
//            if (CheckObjectIsNull.isNull(user)) {
//                return Jsonp.noLoginError("请先登录或重新登录");
//            }
//            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
//            if (CheckObjectIsNull.isNull(workerMember)) {
//                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
//            }
//        } else {
//            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
//        }
//        Map<String,>
//        if(MESSAGE_TYPE_SYS==messageType){
//
//        }else if(MESSAGE_TYPE_UNION==messageType){
//
//        }else if(MESSAGE_TYPE_CLAIM==messageType){
//
//        }else{
//            return Jsonp.paramError("messageType参数异常");
//        }
//        return Jsonp.success();
//    }
}
