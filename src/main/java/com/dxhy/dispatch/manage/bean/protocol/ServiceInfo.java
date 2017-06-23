package com.dxhy.dispatch.manage.bean.protocol;

/**
 * 企业需要的服务的相关信息
 * Created by 赵睿 on 2016/11/16.
 */
public class ServiceInfo {

    private String taxOrgId;

    private String signatureStatus;

    private String taxDiscType;

    private String certEmail;

    private String signatureProxyImg;

    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getTaxOrgId() {
        return taxOrgId;
    }

    public void setTaxOrgId(String taxOrgId) {
        this.taxOrgId = taxOrgId;
    }

    public String getSignatureStatus() {
        return signatureStatus;
    }

    public void setSignatureStatus(String signatureStatus) {
        this.signatureStatus = signatureStatus;
    }

    public String getTaxDiscType() {
        return taxDiscType;
    }

    public void setTaxDiscType(String taxDiscType) {
        this.taxDiscType = taxDiscType;
    }

    public String getCertEmail() {
        return certEmail;
    }

    public void setCertEmail(String certEmail) {
        this.certEmail = certEmail;
    }

    public String getSignatureProxyImg() {
        return signatureProxyImg;
    }

    public void setSignatureProxyImg(String signatureProxyImg) {
        this.signatureProxyImg = signatureProxyImg;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "taxOrgId='" + taxOrgId + '\'' +
                ", signatureStatus='" + signatureStatus + '\'' +
                ", taxDiscType='" + taxDiscType + '\'' +
                ", certEmail='" + certEmail + '\'' +
                ", signatureProxyImg='" + signatureProxyImg + '\'' +
                '}';
    }
}
