package com.aorun.ymgh.controller;


import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.model.WorkerAdvisory;
import com.aorun.ymgh.model.WorkerAttorney;
import com.aorun.ymgh.model.WorkerAttorneyReplyAdvisory;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.service.WorkerAdvisoryService;
import com.aorun.ymgh.service.WorkerAttorneyReplyAdvisoryService;
import com.aorun.ymgh.service.WorkerAttorneyService;
import com.aorun.ymgh.service.WorkerMemberService;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.DateFriendlyShow;
import com.aorun.ymgh.util.biz.ImagePropertiesConfig;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *律师_回复咨询记录
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class WorkerAttorneyReplyAdvisoryRestController {

    @Autowired
    private WorkerAttorneyReplyAdvisoryService workerAttorneyReplyAdvisoryService;

    @Autowired
    private WorkerAdvisoryService workerAdvisoryService;

    @Autowired
    private WorkerMemberService workerMemberService;

    @Autowired
    private WorkerAttorneyService workerAttorneyService;


    /**
     *
     * @param sid
     * @param advisoryId      咨询ID
     * @param requestTimePoint  当前请求时间点
     * * @return
     */
    //1.维权&留言---咨询列表接口
        @RequestMapping(value = "/workerAttorneyReplyAdvisoryList", method = RequestMethod.GET)
        public Object workerAttorneyReplyAdvisoryList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(value="advisoryId")Long advisoryId,
            @RequestParam(value="requestTimePoint")String requestTimePoint
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


            List<WorkerAttorneyReplyAdvisory>    workerAttorneyReplyAdvisoryList = workerAttorneyReplyAdvisoryService.getWorkerAttorneyReplyAdvisoryListByWorkerId(workerId,advisoryId,requestTimePoint);

            HashMap<String,Object> dataMap = new HashMap<String,Object>();
            if(workerAttorneyReplyAdvisoryList!=null&&workerAttorneyReplyAdvisoryList.size()>0)
            {
                requestTimePoint = DateFormat.dateToString(workerAttorneyReplyAdvisoryList.get(0).getReplyTime());
            }

            List<HashMap<String,Object>> dataMapList = new ArrayList<>();
            for (WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory:workerAttorneyReplyAdvisoryList){
                HashMap<String,Object> myDataMap = new HashMap<String,Object>();
                myDataMap.put("replyType",workerAttorneyReplyAdvisory.getReplyType());//
                myDataMap.put("replyContent",workerAttorneyReplyAdvisory.getReplyContent());
                Long attorneyId = workerAttorneyReplyAdvisory.getAttorneyId();//律师ID
                Long myworkerId = workerAttorneyReplyAdvisory.getWorkerId();// 工会用户ID
                WorkerMember myworkerMember = workerMemberService.findWorkerMemberById(myworkerId);
                myDataMap.put("workerMemberImgPath", ImagePropertiesConfig.WORKERMEMBER_SERVER_PATH +myworkerMember.getImgPath()); //用户头像
                WorkerAttorney workerAttorney = workerAttorneyService.findWorkerAttorneyById(attorneyId);
                myDataMap.put("attorneyImgPath",ImagePropertiesConfig.WORKERATTORNEY_SERVER_PATH +workerAttorney.getImgPath()); //律师头像，
                myDataMap.put("nickName",workerAttorney.getNickName());// 律师昵称
                myDataMap.put("replyTime", DateFriendlyShow.showTimeText(workerAttorneyReplyAdvisory.getReplyTime()));
                dataMapList.add(myDataMap);

            }
            dataMap.put("requestTimePoint",requestTimePoint);
            dataMap.put("workerAttorneyReplyAdvisoryList",dataMapList);

        return Jsonp_data.success(dataMap);
    }




    //维权&留言---回复咨询接口
    @RequestMapping(value = "/workerAttorneyReplyAdvisory", method = RequestMethod.POST)
    public Object createWorkerAttorneyReplyAdvisory(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(value="advisoryId", required = true, defaultValue = "")Long advisoryId,
                                        @RequestParam(value="replyContent", required = true, defaultValue = "")String replyContent
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
        WorkerAdvisory workerAdvisory = workerAdvisoryService.findWorkerAdvisoryById(advisoryId);
        if(workerAdvisory!=null){
            WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory = new WorkerAttorneyReplyAdvisory();
            workerAttorneyReplyAdvisory.setWorkerId(workerId);
            workerAttorneyReplyAdvisory.setAdvisoryId(Long.valueOf(advisoryId));
            workerAttorneyReplyAdvisory.setAttorneyId(workerAdvisory.getId());
            workerAttorneyReplyAdvisory.setReplyContent(replyContent);
            workerAttorneyReplyAdvisory.setReplyType(2);//1-律师回复，2-咨询人回复
            workerAttorneyReplyAdvisoryService.saveWorkerAttorneyReplyAdvisory(workerAttorneyReplyAdvisory);
        }else {
            return Jsonp.bussiness_tips_code("暂未找到对应数据!");
        }

        return Jsonp.success();
    }

}
