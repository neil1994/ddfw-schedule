package com.dxhy.dispatch.manage.service.handler;

import com.dxhy.dispatch.manage.bean.tables.HandlerData;
import com.dxhy.dispatch.manage.service.HandlerService;
import com.dxhy.dispatch.utils.HttpServletUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * 类描述
 * Created by 赵睿 on 2016/11/24.
 */
@Service
public class MakeCaAndSignatureHandler extends HandlerService {
    private static final Logger logger = LoggerFactory.getLogger(MakeCaAndSignatureHandler.class);

    @Override
    protected boolean handler(HandlerData handlerData) throws IOException {

        String provider_enableEnterprise = urlConstants.getProviderEnterprise();
        String pk_enterprise_product = queryEnterpriseDetail(provider_enableEnterprise, handlerData.getEnterprise().getEnterpriseBase().getRatepayersCode());
        qz(handlerData.getEleProcessLogs().getEnterprise_id());
        enableEnterprise(provider_enableEnterprise, pk_enterprise_product);
        return true;
    }

    public String queryEnterpriseDetail(String url, String ratepayers_code) throws IOException {
        logger.info("根据企业税号和状态查询企业产品id");
        String respJson = HttpServletUtils.reqeustGet(url + "?status=1&userId=&ratepayers_code=" + ratepayers_code, providerMsg.getProviderCookie());
        JsonNode jsonNode = objectMapper.readTree(respJson);
        jsonNode = jsonNode.findValue("pk_enterprise_product");
        if (jsonNode == null) {
	  logger.error("服务商待审批的企业无此企业，企业税号为{}", ratepayers_code);
	  throw new IOException("服务商待审批的企业无此企业，企业税号为:" + ratepayers_code);
        }
        return jsonNode.asText();
    }

    /**
     * 调用制章制证接口
     *
     * @param eId 企业Id
     * @throws IOException
     */
    public void qz(String eId) throws IOException {
        logger.info("开始制章制证。企业id为{}", eId);
        String resp = HttpServletUtils.reqeustGet(urlConstants.getQz() + eId, providerMsg.getProviderCookie());
        if (resp.contains("制章制证失败")) {
	  throw new IOException("制章制证失败！");
        }

    }

    public void enableEnterprise(String provider_enableEnterprise, String pk_enterprise_product) throws IOException {
        logger.info("开始修改服务商审批企业状态");
        logger.debug("请求路径为:{}", provider_enableEnterprise);
        String jsessionId = providerMsg.getProviderCookie();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPatch patch = new HttpPatch(provider_enableEnterprise + "/" + pk_enterprise_product);
        if (jsessionId != null) {
	  patch.setHeader("Cookie", "JSESSIONID=" + jsessionId);
        }
        CloseableHttpResponse response = httpclient.execute(patch);
        String respJson = HttpServletUtils.dealResponse(provider_enableEnterprise, response);
        JsonNode jsonNode = objectMapper.readTree(respJson);
        String code = jsonNode.findValue("code").asText();
        if ((HttpStatus.SC_OK + "").equals(code)) {

        } else {
	  logger.error("修改服务商审批企业状态，企业产品Id为{}", pk_enterprise_product);
	  throw new IOException("修改服务商审批企业状态，企业产品Id为:" + pk_enterprise_product);
        }

    }
}
