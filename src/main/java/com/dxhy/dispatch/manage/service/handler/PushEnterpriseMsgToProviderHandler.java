package com.dxhy.dispatch.manage.service.handler;

import com.dxhy.dispatch.manage.bean.tables.HandlerData;
import com.dxhy.dispatch.manage.service.HandlerService;
import com.dxhy.dispatch.utils.HttpServletUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵睿 on 2016/11/15.
 * <p>
 * 操作-推送企业信息给服务商
 */
@Service
public class PushEnterpriseMsgToProviderHandler extends HandlerService {
    private final static Logger logger = LoggerFactory.getLogger(PushEnterpriseMsgToProviderHandler.class);

    @Override
    protected boolean handler(HandlerData handlerData) throws IOException {
        certificateCompress(handlerData.getEleProcessLogs().getRatepayers_code(), providerMsg.getProviderCookie());
        push(handlerData.getEleProcessLogs().getRatepayers_code(), providerMsg.getProviderCookie());
        return true;
    }

    /**
     * 发送邮件
     *
     * @param ratePayersCode
     * @param jsessionId
     * @throws IOException
     */
    public void certificateCompress(String ratePayersCode, String jsessionId) throws IOException {
        logger.info("调压缩证书并获得压缩密码接口的参数ratePayersCode={},jsessionId={}", ratePayersCode, jsessionId);
        if (StringUtils.isNotBlank(ratePayersCode) && StringUtils.isNotBlank(jsessionId)) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("", ""));
            String resp = HttpServletUtils.requestPost(urlConstants.getProviderCompress() + ratePayersCode + ".html", nameValuePairs, jsessionId);
            JsonNode jsonNode = objectMapper.readTree(resp);
            String returnCode=jsonNode.findValue("code").asText();
            logger.info("调压缩证书并获得压缩密码接口返回的数据：{}", jsonNode);
            if ("200".equals(returnCode)){
                logger.info("调压缩证书并获得压缩密码成功");
            }else{
                throw new IOException("调压缩证书并获得压缩密码失败");
            }
        }
    }

    /**
     * 推送企业信息
     *
     * @param nsrsbh     纳税人识别号
     * @param jsessionId cookie
     * @throws IOException
     */
    public void push(String nsrsbh, String jsessionId) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("nsrsbh", nsrsbh));
        String resp = HttpServletUtils.requestPost(urlConstants.getProviderPush(), nameValuePairs, jsessionId);
        JsonNode jsonNode = objectMapper.readTree(resp);
        //String returnCode=jsonNode.findValue("returnCode").asText();
        logger.info("推送企业信息接口返回的数据：{}", jsonNode);
        String returnCode = jsonNode.path("response").path("returnCode").asText();
        String returnMessage = jsonNode.path("response").path("returnMessage").asText();
        if (!"0000".equals(returnCode)) {
            logger.error("推送企业资质信息给京东失败，返回信息为:{}", returnMessage);
            throw new IOException(returnMessage);
        }else{
            logger.info("推送企业资质信息给京东成功，返回信息为:{}", returnMessage);
        }
    }
}
