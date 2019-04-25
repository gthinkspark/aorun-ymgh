package com.aorun.ymgh.dto;

public class WorkerCardApplyDto {
    private Long id;

    private Long workerId;

    private Long workerCardId;

    private String applyName;

    private String telephone;

    private String companyName;

    private String idCardNumber;

    private String workerName;

    private String idCardUrls;

    private Integer status;

    private String failReason;

    private Integer isReaded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getWorkerCardId() {
        return workerCardId;
    }

    public void setWorkerCardId(Long workerCardId) {
        this.workerCardId = workerCardId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getIdCardUrls() {
        return idCardUrls;
    }

    public void setIdCardUrls(String idCardUrls) {
        this.idCardUrls = idCardUrls;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Integer getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Integer isReaded) {
        this.isReaded = isReaded;
    }
}