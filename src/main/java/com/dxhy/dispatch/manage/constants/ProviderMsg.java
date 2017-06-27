package com.dxhy.dispatch.manage.constants;


import com.dxhy.dispatch.utils.HttpServletUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * 服务商信息，提供了服务商id以及服务商产品Id等信息
 * Created by 赵睿 on 2016/11/21.
 */
@Service
public class ProviderMsg {

    private static Logger logger = LoggerFactory.getLogger(ProviderMsg.class);

    private static Long expriseTime = 0L;

    private static long sessionExprise = 20 * 60 * 1000;

    @Autowired
    private UrlConstants urlConstants;
    @Autowired
    private ObjectMapper objectMapper;

    private String providerId;

    private String providerProductId;

    private String providerCookie;

    private String loginName;


    /**
     *  获取服务商的登录名
     */
    public void queryProviderByName(String providerName) throws IOException {
        String url=urlConstants.getProvider_project()+urlConstants.getProvider_getName()+"?providerName="+providerName;
        String response = HttpServletUtils.reqeustGet(url);
        JsonNode node = objectMapper.readTree(response);
        String code = node.findValue("code").asText();
        if ("200".equals(code)) {
            loginName = node.findValue("login_name").asText();
            providerId = node.findValue("pk_provider").asText();
            logger.info("获取服务商的登录名成功后，loginName：{}", loginName);
        } else {
            throw new HttpResponseException(Integer.parseInt(code), node.findValue("message").asText());
        }

    }

    private void queryProvider(String pCookie) throws IOException {
        String response = HttpServletUtils.reqeustGet(urlConstants.getProvider(), getProviderCookie());
        JsonNode node = objectMapper.readTree(response);
        String code = node.findValue("code").asText();
        if ("200".equals(code)) {
	  providerId = node.findValue("pk_provider").asText();
	  providerProductId = node.findValue("pk_provider_product").asText();
	  logger.info("用户登录成功后，providerId：{},providerProductId:{}", providerId, providerProductId);
        } else {
	  throw new HttpResponseException(Integer.parseInt(code), node.findValue("message").asText());
        }
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getProviderId() throws IOException {
        if (providerId == null) {
	  queryProvider(providerCookie);
        }
        return providerId;
    }

    public String getProviderProductId() throws IOException {
        if (providerProductId == null) {
	  queryProvider(providerCookie);
        }
        return providerProductId;
    }

    public String getProviderCookie() throws IOException {
        Long now = new Date().getTime();
        if (now > expriseTime + sessionExprise) {
	  providerCookie = HttpServletUtils.login(loginName, urlConstants.getProvider_password(), urlConstants.getProviderLogin(), urlConstants.getCaptcha());
	  expriseTime = now;
        }
        if (providerCookie == null && "".equals(providerCookie)) {
	  providerCookie = HttpServletUtils.login(loginName, urlConstants.getProvider_password(), urlConstants.getProviderLogin(), urlConstants.getCaptcha());
        }
        logger.info("服务商用户登录成功后，providerCookie:{}", providerCookie);
        return providerCookie;
    }

}
