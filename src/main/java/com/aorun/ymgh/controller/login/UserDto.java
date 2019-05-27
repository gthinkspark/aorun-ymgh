package com.aorun.ymgh.controller.login;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserDto() {
    }

//	public UserDto(Integer status, String errorMsg) {
//		this.loginStatus = status;
//		this.errorMsg = errorMsg;
//	}

    /**
     * 用户唯一ID
     */
    private Long memberId;
    /**
     * 类似session ID(判断登录状态)
     */
    private String sid;
    /**
     * 用户名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String imgPath;
    /**
     * 积分
     */
    private int epoints;
//	/** 钱包总金额 */
//	private BigDecimal walletMoney;
//	/** 可用金额 */
//	private BigDecimal availableMoney;
//	/** 会员等级名称 */
//	private String gradeName;
//	/** 登录状态 */
//	private Integer loginStatus;
//	/** 登录失败信息 */
//	private String errorMsg;
    /**
     * 会员手机号
     */
    private String telephone;

    /**
     * 会员类型
     */
    private String memberType;

    private String memberImAccount;


    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getEpoints() {
        return epoints;
    }

    public void setEpoints(int epoints) {
        this.epoints = epoints;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMemberImAccount() {
        return memberImAccount;
    }

    public void setMemberImAccount(String memberImAccount) {
        this.memberImAccount = memberImAccount;
    }


}
