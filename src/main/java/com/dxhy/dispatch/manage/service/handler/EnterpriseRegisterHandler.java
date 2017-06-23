package com.dxhy.dispatch.manage.service.handler;
import com.dxhy.dispatch.manage.bean.protocol.Enterprise;
import com.dxhy.dispatch.manage.bean.protocol.EnterpriseBase;
import com.dxhy.dispatch.manage.bean.tables.EleProcessLogs;
import com.dxhy.dispatch.manage.bean.tables.HandlerData;
import com.dxhy.dispatch.manage.constants.ProviderMsg;
import com.dxhy.dispatch.manage.service.HandlerService;
import com.dxhy.dispatch.utils.HttpServletUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpResponseException;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 赵睿 on 2016/11/15.
 *
 * 操作-企业认证服务
 *
 */
@Service
public class EnterpriseRegisterHandler extends HandlerService {
    private static final Logger logger= LoggerFactory.getLogger(EnterpriseRegisterHandler.class);

    @Autowired
    protected ProviderMsg providerMsg;

    @Override
    protected boolean handler(HandlerData handlerData) throws IOException {
        Enterprise enterprise =handlerData.getEnterprise();
        logger.error("handlerData JsessionId为：{}",handlerData.getJsessionId());
        String resp = registerEnterpriseMsg(enterprise.getEnterpriseBase(), handlerData.getJsessionId());
        JsonNode node=objectMapper.readTree(resp);
        String code= node.findValue("code").asText();
        if(( HttpStatus.SC_OK+"").equals(code)){
            EleProcessLogs eleProcessLogs=handlerData.getEleProcessLogs();
            eleProcessLogs.setProvider_id(providerMsg.getProviderId());
            eleProcessLogs.setEnterprise_id(node.findValue("pkEnterprise").asText());
            scheduleDao.updateProcessLogEnterpriseMsg(eleProcessLogs);
        }else{
            throw new HttpResponseException(Integer.parseInt(code),node.findValue("message").asText());
        }
        return true;
    }

    /**
     * 注册企业详情信息
     * @param enterpriseBase 企业基础信息协议bean
     * @param jSessionId cookie
     * @return 响应信息
     * @throws IOException
     */
    public String registerEnterpriseMsg(EnterpriseBase enterpriseBase, String jSessionId) throws IOException {
        logger.info("开始注册企业");
        String imgUrl=urlConstants.getEnterpriseUploadImg();
        Map<String ,String > map=new HashMap<>();

        map.put("areaId",enterpriseBase.getAreaId());

        map.put("enterpriseType",enterpriseBase.getEnterpriseType());
        if ("1".equals(enterpriseBase.getEnterpriseType())) {
        	map.put("taxRegisterCode",enterpriseBase.getTaxRegisterCode());
        	map.put("taxRegisterPathId",uploadImg(enterpriseBase.getTaxRegisterImg(),imgUrl,jSessionId));
        	map.put("orgCode",enterpriseBase.getOrgCode());
        	map.put("orgPathId",uploadImg(enterpriseBase.getOrgImg(),imgUrl,jSessionId));
        	map.put("businessLicence",enterpriseBase.getBusinessLicence());
        	map.put("businessLicencePathId",uploadImg(enterpriseBase.getBusinessLicenceImg(),imgUrl,jSessionId));
		}else {
			map.put("creditCode",enterpriseBase.getRatepayersCode());
			map.put("creditPathId",uploadImg(enterpriseBase.getCreditImg(),imgUrl,jSessionId));
		}
        map.put("corpName",enterpriseBase.getCorpName());
        map.put("ratepayersCode",enterpriseBase.getRatepayersCode());

        map.put("registerAdd",enterpriseBase.getRegisterAdd());
        map.put("operationsAdd",enterpriseBase.getOperationsAdd());
        map.put("contracts",enterpriseBase.getContracts());
        map.put("contractsPhone",enterpriseBase.getContractsPhone());
        map.put("contractsEmail",enterpriseBase.getContractsEmail());
        map.put("contractsCard",enterpriseBase.getContractsCard());
        map.put("contractsCardPath1Id",uploadImg(enterpriseBase.getContractsCardImg1(),imgUrl,jSessionId));
        map.put("contractsCardPath2Id",uploadImg(enterpriseBase.getContractsCardImg2(),imgUrl,jSessionId));
        return HttpServletUtils.requestPost(urlConstants.getEnterpriseRegisterUrl(),map,jSessionId);
    }

    /**	
     * 图片上传，返回图片路径
     * @param img base64转码之后的图片
     * @param imgUrl 图片上传的url
     * @param jsessionId cookie
     * @return 图片在服务器路径
     * @throws IOException
     */
    public String uploadImg(String img, String imgUrl, String jsessionId) throws IOException {
        logger.info("开始上传企业图片信息,图片为:{},上传地址为：{}",img.length(),imgUrl);
        List<NameValuePair> params =new ArrayList<>();
        params.add(new BasicNameValuePair("imgData",img));
        String resp= HttpServletUtils.requestPost(imgUrl,params,jsessionId);
        JsonNode node=objectMapper.readTree(resp);
        String code= node.findValue("code").asText();
        if("200".equals(code)){
            resp= node.findValue("rows").asText();
        }else{
            throw new HttpResponseException(Integer.parseInt(code),node.findValue("message").asText());
        }
        return resp;
    }


}
