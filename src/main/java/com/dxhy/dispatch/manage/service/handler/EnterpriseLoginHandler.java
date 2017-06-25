package com.dxhy.dispatch.manage.service.handler;

import com.dxhy.dispatch.manage.bean.protocol.Enterprise;
import com.dxhy.dispatch.manage.bean.protocol.EnterpriseBase;
import com.dxhy.dispatch.manage.bean.tables.HandlerData;
import com.dxhy.dispatch.manage.service.HandlerService;
import com.dxhy.dispatch.utils.HttpServletUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpResponseException;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 赵睿 on 2016/11/15.
 * <p>
 * 操作-企业登录服务,首先注册企业用户，然后使用企业用户登录
 */
@Service
public class EnterpriseLoginHandler extends HandlerService {
    private static final Logger logger = LoggerFactory.getLogger(EnterpriseLoginHandler.class);

    private final static String ele = "Ele";

    @Override
    protected boolean isAlways() {
        return true;
    }

    @Override
    protected boolean handler(HandlerData handlerData) throws IOException {
        Enterprise enterprise = handlerData.getEnterprise();
        EnterpriseBase enterpriseBase = enterprise.getEnterpriseBase();
        String ratePayersCode = enterpriseBase.getRatepayersCode();
        String userName = ele + ratePayersCode;
        String password = ele + ratePayersCode.substring(ratePayersCode.length() - 6);
        String loginUrl = urlConstants.getEnterpriseLoginUrl();

        String jsessionId = HttpServletUtils.login(userName, password, loginUrl, urlConstants.getCaptcha());
        if (jsessionId == null) {
	  logger.info("用户名为{}的用户，登录失败，启用注册服务");
	  jsessionId = HttpServletUtils.getClientCookies(new URL(loginUrl));
	  register(userName, password, enterpriseBase.getContractsPhone(), urlConstants.getEnterpriseAccout(), jsessionId);
	  jsessionId = HttpServletUtils.login(userName, password, loginUrl, urlConstants.getCaptcha());
        }
        handlerData.setJsessionId(jsessionId);
        return true;
    }


    /**
     * 用户注册，获得cookie
     *
     * @param userName    用户名
     * @param password    密码
     * @param phone       电话
     * @param registerUrl 登记url
     * @param jsessionId  cookieId
     * @throws IOException
     */
    public void register(String userName, String password, String phone, String registerUrl, String jsessionId) throws IOException {
        logger.info("用户名为{}的企业开始进行用户注册", userName);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("loginName", userName));
        params.add(new BasicNameValuePair("loginPassword", password));
        params.add(new BasicNameValuePair("telephone", phone));
        String resp = HttpServletUtils.requestPost(registerUrl, params, jsessionId);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(resp);
        String code = node.findValue("code").asText();
        if (!"200".equals(code)) {
	  throw new HttpResponseException(Integer.parseInt(code), node.findValue("message").asText());
        }
    }
}
