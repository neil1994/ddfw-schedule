package com.dxhy.dispatch.manage.service.handler;

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
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderApproveEnterpriseHandler extends HandlerService {
    private static final Logger logger= LoggerFactory.getLogger(ProviderApproveEnterpriseHandler.class);

    @Override
    protected boolean handler(HandlerData handlerData) throws IOException {
        String img =handlerData.getEnterprise().getServiceInfo().getSignatureProxyImg();
        uploadImg(handlerData.getEleProcessLogs().getEnterprise_id()+"/2/",img,providerMsg.getProviderCookie());
        return true;
    }

    /**
     * 图片上传-针对于签章协议上传
     * @param path 图片上传路径
     * @param img base64转码之后的图片文件
     * @param jsessionId cookie
     */
    public void uploadImg(String path, String img, String jsessionId) throws IOException {
        logger.info("上传企业签章协议");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("path", path));
        params.add(new BasicNameValuePair("img", img));
        String resp= HttpServletUtils.requestPost(urlConstants.getProviderUploadImg(),params,jsessionId);
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode node=objectMapper.readTree(resp);
        String code= node.findValue("code").asText();
        if(!"200".equals(code)){
            throw new HttpResponseException(Integer.parseInt(code),node.findValue("message").asText());
        }
    }


}
