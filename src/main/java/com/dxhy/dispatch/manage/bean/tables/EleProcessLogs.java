package com.dxhy.dispatch.manage.bean.tables;


import com.dxhy.dispatch.utils.CalendarUtil;
import com.dxhy.dispatch.utils.DistributedKeyMaker;

import java.util.Date;

/**
 * 对ele_process_logs表的处理
 * Created by 赵睿 on 2016/11/17.
 */
@SuppressWarnings("unused")
public class EleProcessLogs {
    private String pk_process_logs;

    private String provider_id;

    private String enterprise_id;

    private String ratepayers_code;

    private String process_code;

    private String process_msg;

    private String process_status;

    private String notice_status;

    private String create_time;

    public String getPk_process_logs() {
        return pk_process_logs;
    }

    public void setPk_process_logs(String pk_process_logs) {
        this.pk_process_logs = pk_process_logs;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getRatepayers_code() {
        return ratepayers_code;
    }

    public void setRatepayers_code(String ratepayers_code) {
        this.ratepayers_code = ratepayers_code;
    }

    public String getProcess_code() {
        return process_code;
    }

    @SuppressWarnings("SameParameterValue")
    public void setProcess_code(String process_code) {
        this.process_code = process_code;
    }

    public String getProcess_msg() {
        return process_msg;
    }

    public void setProcess_msg(String process_msg) {
        this.process_msg = process_msg;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }

    public String getNotice_status() {
        return notice_status;
    }

    public void setNotice_status(String notice_status) {
        this.notice_status = notice_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public EleProcessLogs() {
    }

    public EleProcessLogs(String provider_id, String enterprise_id, String ratepayers_code, String process_code, String process_msg, String process_status, String notice_status) {
        this.provider_id = provider_id;
        this.enterprise_id = enterprise_id;
        this.ratepayers_code = ratepayers_code;
        this.process_code = process_code;
        this.process_msg = process_msg;
        this.process_status = process_status;
        this.notice_status = notice_status;

        this.pk_process_logs= DistributedKeyMaker.getInstance().generateShotKey();
        this.create_time= CalendarUtil.convertDateTimeToString(new Date(),true);

    }



    public EleProcessLogs updateEleProcessLogs(String process_code,String process_msg,String process_status,String notice_status){
        this.process_code = process_code;
        this.process_msg = process_msg;
        this.process_status = process_status;
        this.notice_status = notice_status;
        return this;
    }

    @Override
    public String toString() {
        return "EleProcessLogs{" +
                "pk_process_logs='" + pk_process_logs + '\'' +
                ", provider_id='" + provider_id + '\'' +
                ", enterprise_id='" + enterprise_id + '\'' +
                ", ratepayers_code='" + ratepayers_code + '\'' +
                ", process_code='" + process_code + '\'' +
                ", process_msg='" + process_msg + '\'' +
                ", process_status='" + process_status + '\'' +
                ", notice_status='" + notice_status + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
