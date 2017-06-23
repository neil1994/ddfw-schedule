package com.dxhy.dispatch.manage.enumConfig;

/**
 * 调度枚举类，调度状态以及是否需要提示服务商状态
 * Created by 赵睿 on 2016/11/16.
 */
@SuppressWarnings("unused")
public enum HandlerEnum {
    StartHandler(0,"开始调度服务",false,false),
    EnterpriseCheckHandler(10, "校验企业信息",false,false),
    EnterpriseLoginHandler(11, "企业账户注册或登录", false,false),
    EnterpriseRegisterHandler(12, "企业登记",true,true),
    EnterpriseApplyForServiceHandler(13, "企业服务申请", true,true),
    ProviderApproveEnterpriseHandler(14, "服务商审批企业",false, false),
    MakeCaAndSignatureHandler(15,"制章制证",true,true),
    PushEnterpriseMsgToProviderHandler(16, "推送企业信息",false ,true);

    int code;
    String describe;
    boolean beforeSend; //带受理-成功，
    boolean afterSend;

    HandlerEnum(int code, String describe, boolean beforeSend, boolean afterSend) {
        this.code = code;
        this.describe = describe;
        this.beforeSend=beforeSend;
        this.afterSend=afterSend;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isBeforeSend() {
        return beforeSend;
    }

    public void setBeforeSend(boolean beforeSend) {
        this.beforeSend = beforeSend;
    }

    public boolean isAfterSend() {
        return afterSend;
    }

    public void setAfterSend(boolean afterSend) {
        this.afterSend = afterSend;
    }

    public static HandlerEnum valueOf(int statusCode) {
        for (HandlerEnum status : values()) {
            if (status.code==(statusCode)) {
                return status;
            }
        }
        throw new IllegalArgumentException("没有匹配的元素： [" + statusCode + "]");
    }
}
