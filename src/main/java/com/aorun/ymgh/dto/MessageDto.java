package com.aorun.ymgh.dto;

import com.aorun.ymgh.model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 作者 :LG
 * @version 创建时间：2016年8月4日 下午3:48:33
 * 类说明:
 */
public class MessageDto{
//	public class MessageDto implements Comparable<MessageDto>{
	private Long id;
	
    private String title;

    private Long time;

    private String link;

    private String body;
    
    private Long memberId;

    private String iconUrl;
    
    private int isReade=0;	//0未读   1已读
    
	public MessageDto(Long id,String title, Date time, String link, String body,String iconUrl) {
		super();
		this.id = id;
		this.title = title;
		this.time = time.getTime();
		this.link = link;
		this.body = body;
		this.iconUrl = iconUrl;
	}

	public MessageDto() {
	}

    public static MessageDto transToDto(Message message){
        if(null==message)
            return null;
        return new MessageDto(message.getId(),message.getTitle(),message.getTime(),message.getLink(),message.getBody(),message.getIconUrl());
    }

    public static List<MessageDto> transToDtoList(List<Message> messageList){
	    List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
        if(null!=messageList&&messageList.size()>0) {
            for (Message message : messageList) {
                messageDtoList.add(transToDto(message));
            }
        }
        return messageDtoList;
    }


    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIsReade() {
		return isReade;
	}

	public void setIsReade(int isReade) {
		this.isReade = isReade;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public int compareTo(MessageDto o) {
		if(null==o)
			return 1;
		if(this.time<o.time)
			return 1;
		return 0;
	}
    
}
