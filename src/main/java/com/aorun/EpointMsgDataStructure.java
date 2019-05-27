package com.aorun;

import java.io.Serializable;

/**
 * 类描述:积分消息数据结构
 * Created by duxihu on 2019/5/23 0023.
 */
public class EpointMsgDataStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long workerId;
    private String msgId;//随机唯一ID
    private String epointConfigCode;//积分配置编码  必填
    private Integer epoint;  //选填(默认为0)
    private String bizUniqueSignCode;//业务唯一标识码

//    id
//    worker_id
//    msgId 随机唯一ID    必填
//    epoint_config_code 积分配置编码  必填
//    type                             1-积分消息
//    epoint--选填(默认为0),
//    opertype,1-增加积分，2-减少积分
//    业务唯一标识码  biz_unique_sign_code
//    remark 备注
//    status  状态                   1-成功，2-失败


    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getEpointConfigCode() {
        return epointConfigCode;
    }

    public void setEpointConfigCode(String epointConfigCode) {
        this.epointConfigCode = epointConfigCode;
    }

    public Integer getEpoint() {
        return epoint;
    }

    public void setEpoint(Integer epoint) {
        this.epoint = epoint;
    }

    public String getBizUniqueSignCode() {
        return bizUniqueSignCode;
    }

    public void setBizUniqueSignCode(String bizUniqueSignCode) {
        this.bizUniqueSignCode = bizUniqueSignCode;
    }

    @Override
    public String toString() {
        return "EpointMsgDataStructure{" +
                "workerId=" + workerId +
                ", msgId='" + msgId + '\'' +
                ", epointConfigCode='" + epointConfigCode + '\'' +
                ", epoint=" + epoint +
                ", bizUniqueSignCode='" + bizUniqueSignCode + '\'' +
                '}';
    }


}
