package com.dxhy.dispatch.manage.service.handler;

import com.dxhy.dispatch.manage.bean.protocol.Enterprise;
import com.dxhy.dispatch.manage.bean.protocol.EnterpriseBase;
import com.dxhy.dispatch.manage.bean.protocol.ServiceInfo;
import com.dxhy.dispatch.manage.bean.tables.HandlerData;
import com.dxhy.dispatch.manage.service.HandlerService;
import com.dxhy.dispatch.manage.utils.CheckEnterpriseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.dxhy.dispatch.manage.constants.RegexEnum.codeRegex13Or15;
import static com.dxhy.dispatch.manage.constants.RegexEnum.codeRegex15_20;
import static com.dxhy.dispatch.utils.CheckUtils.paramcheck;


/**
 * Created by 赵睿 on 2016/11/15.
 * <p>
 * 操作-企业参数校验
 */
@Service
public class EnterpriseCheckHandler extends HandlerService {
    private static final Logger logger = LoggerFactory.getLogger(EnterpriseCheckHandler.class);

    @Override
    protected boolean handler(HandlerData handlerData) throws IOException {
        Enterprise enterprise = handlerData.getEnterprise();
        checkParamters(enterprise.getEnterpriseBase(), enterprise.getServiceInfo());
        return true;
    }

    /**
     * 参数校验
     *
     * @param enterpriseBase 企业基础信息协议bean
     * @param serviceInfo    企业服务信息协议bean
     * @throws IOException
     */
    private void checkParamters(EnterpriseBase enterpriseBase, ServiceInfo serviceInfo) throws IOException {
        logger.info("开始数据校验");

        paramcheck(enterpriseBase.getCorpName(), true, 200, "纳税人名称");
        paramcheck(enterpriseBase.getAreaId(), true, 2, "地区代码");
        paramcheck(enterpriseBase.getEnterpriseType(), true, 1, "是否三证合一");
        if ("1".equals(enterpriseBase.getEnterpriseType())) {
	  paramcheck(enterpriseBase.getTaxRegisterCode(), true, 0, "校验税务机关登记证号", codeRegex15_20);
	  paramcheck(enterpriseBase.getTaxRegisterImg(), true, 0, "税务登记证照文件");
	  paramcheck(enterpriseBase.getOrgCode(), true, 20, "组织结构代码");
	  paramcheck(enterpriseBase.getOrgImg(), true, 0, "组织结构证照文件");
	  paramcheck(enterpriseBase.getBusinessLicence(), true, 0, "营业执照号码", codeRegex13Or15);
	  paramcheck(enterpriseBase.getBusinessLicenceImg(), true, 0, "营业执照文件");
        } else {
	  paramcheck(enterpriseBase.getRatepayersCode(), true, 0, "纳税人识别号(社会信用代码)", codeRegex15_20);
	  paramcheck(enterpriseBase.getCreditImg(), true, 0, "三证合一证件图片");
        }
        paramcheck(enterpriseBase.getRegisterAdd(), true, 300, "注册地址");
        paramcheck(enterpriseBase.getOperationsAdd(), true, 300, "生产经营地址");
        paramcheck(enterpriseBase.getContracts(), true, 20, "联系人");
        paramcheck(enterpriseBase.getContractsPhone(), true, 20, "联系电话");
        paramcheck(enterpriseBase.getContractsEmail(), true, 50, "联系人邮箱");
        paramcheck(enterpriseBase.getContractsCard(), true, 20, "联系身份证号");
        paramcheck(enterpriseBase.getContractsCardImg1(), true, 0, "联系身份证复印件正面");
        paramcheck(enterpriseBase.getContractsCardImg2(), true, 0, "联系身份证复印件背面");

        paramcheck(serviceInfo.getTaxOrgId(), true, 11, "税务机关代码");
        paramcheck(serviceInfo.getSignatureStatus(), true, 1, "是否已有电子签章");
        paramcheck(serviceInfo.getTaxDiscType(), true, 1, "金税盘类型");
        paramcheck(serviceInfo.getCertEmail(), true, 50, "接收证书邮箱地址");
        paramcheck(serviceInfo.getSignatureProxyImg(), true, 0, "电子签章制作委托书");

        try {
	  CheckEnterpriseUtils.IDCardValidate(enterpriseBase.getContractsCard());
        } catch (Exception e) {
	  throw new IOException(e.getMessage());
        }
    }
}
