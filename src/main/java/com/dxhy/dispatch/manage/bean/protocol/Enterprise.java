package com.dxhy.dispatch.manage.bean.protocol;

/**
 * 企业申请电子发票 协议头
 * Created by 赵睿 on 2016/11/16.
 */
@SuppressWarnings("unused")
public class Enterprise {
    private EnterpriseBase enterpriseBase;
    private ServiceInfo serviceInfo;

    public EnterpriseBase getEnterpriseBase() {
        return enterpriseBase;
    }

    public void setEnterpriseBase(EnterpriseBase enterpriseBase) {
        this.enterpriseBase = enterpriseBase;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public Enterprise() {
    }

    public Enterprise(EnterpriseBase enterpriseBase, ServiceInfo serviceInfo) {
        this.enterpriseBase = enterpriseBase;
        this.serviceInfo = serviceInfo;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "enterpriseBase=" + enterpriseBase +
                ", serviceInfo=" + serviceInfo +
                '}';
    }
}
