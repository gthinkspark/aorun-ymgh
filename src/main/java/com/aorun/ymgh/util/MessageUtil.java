package com.aorun.ymgh.util;

import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.dto.MessageDto;
import com.aorun.ymgh.model.Message;
import com.aorun.ymgh.model.MessageReade;
import com.aorun.ymgh.service.MessageReadeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MessageUtil
 * @Description: TODO
 * @author: lg
 * @date: 2019/4/4 16:53
 */
public class MessageUtil {
    public static final Integer MESSAGE_STATU_ISDEL_NO=1;
    public static final Integer MESSAGE_STATU_ISDEL_OK=0;

    public static final Integer MESSAGE_CHECK_OK=1;
    public static final Integer MESSAGE_CHECK_NO=0;

    public static final Integer MESSAGE_READE_OK=1;
    public static final Integer MESSAGE_READE_NO=0;

    public static final Integer MESSAGE_TYPE_SYS=1;
    public static final Integer MESSAGE_TYPE_UNION=2;
    public static final Integer MESSAGE_TYPE_CLAIM=3;

    public static List<MessageDto> setMessageReade(UserDto user, List<Message> messageList, MessageReadeService messageReadeService) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        if(null!=messageList&&messageList.size()>0){
            messageDtoList = MessageDto.transToDtoList(messageList);
        }
        Map<String, Object> readMap = new HashMap<String, Object>();
        readMap.put("memberId", user.getMemberId());
        for (MessageDto messageDto : messageDtoList) {
            readMap.put("messageId", messageDto.getMemberId());
            List<MessageReade> messageReadeList = messageReadeService.findByMap(readMap);
            if (null != messageReadeList && messageReadeList.size() > 0) {
                messageDto.setIsReade(MessageUtil.MESSAGE_READE_OK);
            }
        }
        return messageDtoList;
    }
}
