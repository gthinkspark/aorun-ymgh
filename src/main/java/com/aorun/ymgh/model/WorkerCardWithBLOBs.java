package com.aorun.ymgh.model;

public class WorkerCardWithBLOBs extends WorkerCard {
    private String functionContent;

    private String applyConditionContent;

    private String serviceConditionContent;

    public String getFunctionContent() {
        return functionContent;
    }

    public void setFunctionContent(String functionContent) {
        this.functionContent = functionContent == null ? null : functionContent.trim();
    }

    public String getApplyConditionContent() {
        return applyConditionContent;
    }

    public void setApplyConditionContent(String applyConditionContent) {
        this.applyConditionContent = applyConditionContent == null ? null : applyConditionContent.trim();
    }

    public String getServiceConditionContent() {
        return serviceConditionContent;
    }

    public void setServiceConditionContent(String serviceConditionContent) {
        this.serviceConditionContent = serviceConditionContent == null ? null : serviceConditionContent.trim();
    }
}