package com.dxhy.dispatch.manage.service.handler;

import com.dxhy.dispatch.manage.bean.protocol.Enterprise;
import com.dxhy.dispatch.manage.bean.protocol.ServiceInfo;
import com.dxhy.dispatch.manage.bean.tables.HandlerData;
import com.dxhy.dispatch.manage.constants.ProviderMsg;
import com.dxhy.dispatch.manage.service.HandlerService;
import com.dxhy.dispatch.utils.HttpServletUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.dxhy.dispatch.manage.constants.SystemConstants.charset;


/**
 * Created by 赵睿 on 2016/11/15.
 * <p>
 * 操作-企业申请服务
 */
@Service
public class EnterpriseApplyForServiceHandler extends HandlerService {
    private static final Logger logger = LoggerFactory.getLogger(EnterpriseApplyForServiceHandler.class);

    @Autowired
    protected ProviderMsg providerMsg;


    @Override
    protected boolean handler(HandlerData handlerData) throws IOException {
        Enterprise enterprise = handlerData.getEnterprise();
        ServiceInfo serviceInfo = enterprise.getServiceInfo();

        Map<String, String> map = new HashMap<>();

        map.put("providerproductid", providerMsg.getProviderProductId());
        map.put("tax_org_code", serviceInfo.getTaxOrgId());
        map.put("sign", serviceInfo.getSignatureStatus());//待审核
        map.put("tax_disc_type", serviceInfo.getTaxDiscType());
        map.put("cert_email", serviceInfo.getCertEmail());
        map.put("city_org_code", "");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(urlConstants.getEnterpriseSaveServiceRequest());
        post.setEntity(new UrlEncodedFormEntity(HttpServletUtils.toNameValuePairs(map), charset));
        post.setHeader("Cookie", "JSESSIONID=" + handlerData.getJsessionId());
        CloseableHttpResponse response = httpclient.execute(post);

        StatusLine statusLine = response.getStatusLine();
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity);
        logger.debug("使用post请求方式,返回状态为{}，请求返回的报文实体数据为:{}", statusLine, result);


        return true;
    }
}
