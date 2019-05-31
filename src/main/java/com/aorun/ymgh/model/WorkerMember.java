package com.aorun.ymgh.model;

import java.util.Date;

public class WorkerMember {
    private Long id;

    private Long memberId;

    private String nickName;

    private String loginName;

    private String password;

    private Date createTime;

    private Date updateTime;

    private String telephone;

    private String email;

    private String imgPath;

    private String disable;

    private Integer epoints;

    private String epointLevel;

    private String macAddr;

    private String pushSignAddr;

    private String bankCardNumber;

    private String issuingBank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable == null ? null : disable.trim();
    }

    public Integer getEpoints() {
        return epoints;
    }

    public void setEpoints(Integer epoints) {
        this.epoints = epoints;
    }

    public String getEpointLevel() {
        return epointLevel;
    }

    public void setEpointLevel(String epointLevel) {
        this.epointLevel = epointLevel == null ? null : epointLevel.trim();
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr == null ? null : macAddr.trim();
    }

    public String getPushSignAddr() {
        return pushSignAddr;
    }

    public void setPushSignAddr(String pushSignAddr) {
        this.pushSignAddr = pushSignAddr == null ? null : pushSignAddr.trim();
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber == null ? null : bankCardNumber.trim();
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank == null ? null : issuingBank.trim();
    }
}