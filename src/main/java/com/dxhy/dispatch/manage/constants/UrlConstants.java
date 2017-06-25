package com.dxhy.dispatch.manage.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * url常量，读取配置文件，配置请求路径
 * Created by 赵睿 on 2016/11/19.
 */
@ConfigurationProperties(prefix = "dxhy.schedule")
public class UrlConstants {
    private String enterprise_account;
    private String enterprise_upload;
    private String enterprise_register;
    private String enterprise_saveServiceRequest;


    private String captcha;


    private String provider_userName;
    private String provider_password;
    private String provider_login;
    private String provider_query;
    private String provider_qz;
    private String provider_uploadImg;
    private String provider_push;
    private String provider_pushNotice;
    private String provider_Enterprise;
    //证书压缩
    private String provider_Compress;

    private String enterprise_project;
    private String provider_project;

    public String getEnterprise_account() {
        return enterprise_account;
    }

    public void setEnterprise_account(String enterprise_account) {
        this.enterprise_account = enterprise_account;
    }

    public String getEnterprise_upload() {
        return enterprise_upload;
    }

    public void setEnterprise_upload(String enterprise_upload) {
        this.enterprise_upload = enterprise_upload;
    }

    public String getEnterprise_register() {
        return enterprise_register;
    }

    public void setEnterprise_register(String enterprise_register) {
        this.enterprise_register = enterprise_register;
    }

    public String getEnterprise_saveServiceRequest() {
        return enterprise_saveServiceRequest;
    }

    public void setEnterprise_saveServiceRequest(String enterprise_saveServiceRequest) {
        this.enterprise_saveServiceRequest = enterprise_saveServiceRequest;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public void setProvider_userName(String provider_userName) {
        this.provider_userName = provider_userName;
    }

    public void setProvider_password(String provider_password) {
        this.provider_password = provider_password;
    }

    public String getProvider_login() {
        return provider_login;
    }

    public void setProvider_login(String provider_login) {
        this.provider_login = provider_login;
    }

    public String getProvider_query() {
        return provider_query;
    }

    public void setProvider_query(String provider_query) {
        this.provider_query = provider_query;
    }

    public String getProvider_qz() {
        return provider_qz;
    }

    public void setProvider_qz(String provider_qz) {
        this.provider_qz = provider_qz;
    }

    public String getProvider_uploadImg() {
        return provider_uploadImg;
    }

    public void setProvider_uploadImg(String provider_uploadImg) {
        this.provider_uploadImg = provider_uploadImg;
    }

    public String getProvider_push() {
        return provider_push;
    }

    public void setProvider_push(String provider_push) {
        this.provider_push = provider_push;
    }

    public String getProvider_pushNotice() {
        return provider_pushNotice;
    }

    public void setProvider_pushNotice(String provider_pushNotice) {
        this.provider_pushNotice = provider_pushNotice;
    }

    public String getProvider_Enterprise() {
        return provider_Enterprise;
    }

    public void setProvider_Enterprise(String provider_Enterprise) {
        this.provider_Enterprise = provider_Enterprise;
    }

    public String getProvider_Compress() {
        return provider_Compress;
    }

    public void setProvider_Compress(String provider_Compress) {
        this.provider_Compress = provider_Compress;
    }

    public String getEnterprise_project() {
        return enterprise_project;
    }

    public void setEnterprise_project(String enterprise_project) {
        this.enterprise_project = enterprise_project;
    }

    public String getProvider_project() {
        return provider_project;
    }

    public void setProvider_project(String provider_project) {
        this.provider_project = provider_project;
    }

    public String getEnterpriseLoginUrl() {
        return  enterprise_project + "index.html";
    }

    public String getEnterpriseAccout(){
        return enterprise_project+enterprise_account;
    }

    public String getEnterpriseUploadImg() {
        return enterprise_project +enterprise_upload;
    }

    public String getEnterpriseRegisterUrl() {
        return enterprise_project + enterprise_register;
    }

    public String getEnterpriseSaveServiceRequest() {
        return enterprise_project +enterprise_saveServiceRequest;
    }

    public String getProviderLogin() {
        return  provider_project +provider_login;
    }

    public String getProvider() {
        return provider_project +provider_query;
    }

    public String getQz() {
        return  provider_project +provider_qz;
    }

    public String getProviderUploadImg() {
        return  provider_project +provider_uploadImg;
    }

    public String getProviderPush(){
        return  provider_project +provider_push;
    }

    public String getProvider_userName() {
        return provider_userName;
    }

    public String getProvider_password() {
        return provider_password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getProviderPushNotice(){
        return provider_project+provider_pushNotice;
    }


    public String getProviderEnterprise(){
        return provider_project+provider_Enterprise;
    }
    public String getProviderCompress() {
		return provider_project+provider_Compress;
	}

    @Override
    public String toString() {
        return "UrlConstants{" +
                "enterprise_account='" + enterprise_account + '\'' +
                ", enterprise_upload='" + enterprise_upload + '\'' +
                ", enterprise_register='" + enterprise_register + '\'' +
                ", enterprise_saveServiceRequest='" + enterprise_saveServiceRequest + '\'' +
                ", captcha='" + captcha + '\'' +
                ", provider_userName='" + provider_userName + '\'' +
                ", provider_password='" + provider_password + '\'' +
                ", provider_login='" + provider_login + '\'' +
                ", provider_query='" + provider_query + '\'' +
                ", provider_qz='" + provider_qz + '\'' +
                ", provider_uploadImg='" + provider_uploadImg + '\'' +
                ", provider_push='" + provider_push + '\'' +
                ", provider_pushNotice='" + provider_pushNotice + '\'' +
                ", provider_Enterprise='" + provider_Enterprise + '\'' +
                ", provider_Compress='" + provider_Compress + '\'' +
                ", enterprise_project='" + enterprise_project + '\'' +
                ", provider_project='" + provider_project + '\'' +
                '}';
    }
}
