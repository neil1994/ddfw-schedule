package com.dxhy.dispatch.manage.bean.tables;


import com.dxhy.dispatch.manage.bean.protocol.Enterprise;

/**
 * 调度数据-存储在调度服务需要的数据
 * Created by 赵睿 on 2016/11/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class HandlerData {
    protected Enterprise enterprise;

    protected String log_id;

    protected String jsessionId;

    protected EleProcessLogs eleProcessLogs;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public EleProcessLogs getEleProcessLogs() {
        return eleProcessLogs;
    }

    public String getJsessionId() {
        return jsessionId;
    }

    public void setJsessionId(String jsessionId) {
        this.jsessionId = jsessionId;
    }

    public void setEleProcessLogs(EleProcessLogs eleProcessLogs) {
        this.eleProcessLogs = eleProcessLogs;
    }


    public HandlerData() {
    }

    public HandlerData(Enterprise enterprise, String log_id) {
        this.enterprise = enterprise;
        this.log_id = log_id;
    }


}
