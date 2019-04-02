package com.aorun.ymgh.controller;

import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.model.Message;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.service.MessageService;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.biz.UnionUtil;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final Integer MESSAGE_STATU_ISDEL_NO=1;
    private final Integer MESSAGE_STATU_ISDEL_OK=0;

    private final Integer MESSAGE_TYPE_SYS=1;
    private final Integer MESSAGE_TYPE_UNION=2;
    private final Integer MESSAGE_TYPE_CLAIM=3;

    //1.列表接口----分页查询
    @RequestMapping(value = "/messageList/{type}", method = RequestMethod.GET)
    public Object messageList(
            @PathVariable("type") Integer type,
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name="pageIndex", required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(name="pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {
        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
            if (CheckObjectIsNull.isNull(user)) {
                return Jsonp.noLoginError("请先登录或重新登录");
            }
            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user), WorkerMember.class);
            if (CheckObjectIsNull.isNull(workerMember)) {
                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
            }
        } else {
            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
        }
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("statu",MESSAGE_STATU_ISDEL_NO);
        params.put("start",(pageIndex-1)*pageSize);
        params.put("limit",pageIndex*pageSize);
        params.put("type",type);
        if(type!=MESSAGE_TYPE_SYS){
            params.put("memberId",user.getMemberId());
        }
        List<Message> messageList = messageService.findByMap(params);
        resultMap.put("messageList",messageList);
        return Jsonp_data.success(resultMap);
    }

    //2.消息首页接口----分页查询
    @RequestMapping(value = "/messageHomeList", method = RequestMethod.GET)
    public Object messageHomeList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
    ) {
        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
//            if (CheckObjectIsNull.isNull(user)) {
//                return Jsonp.noLoginError("请先登录或重新登录");
//            }
//            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user), WorkerMember.class);
//            if (CheckObjectIsNull.isNull(workerMember)) {
//                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
//            }
        }
//        else {
//            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
//        }
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("type",MESSAGE_TYPE_SYS);
        params.put("statu",MESSAGE_STATU_ISDEL_NO);
        params.put("start",0);
        params.put("limit",1);
        List<Message> sysMessageList = messageService.findByMap(params);
        List<Message> unionMessageList = new ArrayList<>();
        List<Message> claimMessageList = new ArrayList<>();
        if(null!=user){
            params.put("memberId",user.getMemberId());
            params.put("type",MESSAGE_TYPE_UNION);
            unionMessageList = messageService.findByMap(params);
            params.put("type",MESSAGE_TYPE_CLAIM);
            claimMessageList = messageService.findByMap(params);
        }

        resultMap.put("sysMessageList",sysMessageList);
        resultMap.put("unionMessageList",unionMessageList);
        resultMap.put("claimMessageList",claimMessageList);
        return Jsonp_data.success(resultMap);
    }

}
