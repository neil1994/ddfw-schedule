package com.dxhy.dispatch;

import com.dxhy.dispatch.manage.bean.protocol.Data;
import com.dxhy.dispatch.manage.bean.protocol.Enterprise;
import com.dxhy.dispatch.manage.bean.protocol.EnterpriseBase;
import com.dxhy.dispatch.manage.bean.protocol.ServiceInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DdfwScheduleApplicationTests {

	@Test
	public void contextLoads() {

		Enterprise enterprise=new Enterprise();
		EnterpriseBase enterpriseBase=new EnterpriseBase();
		enterpriseBase.setAreaId("sdsdsdsdsds");
		enterpriseBase.setBusinessLicence("asadasd");
		enterpriseBase.setBusinessLicenceImg("dsssssssssssssssssssssssssssssssssssssssssssssssssssss");
		enterpriseBase.setContracts("dssdsddsdsdsdsd");
		ServiceInfo serviceInfo=new ServiceInfo();
		serviceInfo.setCertEmail("2121212121212");
		serviceInfo.setProviderName("FDJKFHLKJSDFSF");
		serviceInfo.setSignatureProxyImg("sdsddddddddddddd");
		serviceInfo.setTaxOrgId("dsdsdsdsd");
		enterprise.setEnterpriseBase(enterpriseBase);
		enterprise.setServiceInfo(serviceInfo);
        Data data=new Data();
        data.setEnterprise(enterprise);
		ObjectMapper objectMapper=new ObjectMapper();
		try {
            String valueAsString = objectMapper.writeValueAsString(data);
            System.out.println(valueAsString);
        } catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void testStringToPojo(){
        String json="{\n" +
                "\"enterprise\": {\n" +
                "\"enterpriseBase\": {\n" +
                "\"corpName\": \"北京朗万科技发展有限公司\",\n" +
                "\"ratepayersCode\": \"110227745489496\",\n" +
                "\"areaId\": \"11\",\n" +
                "\"enterpriseType\": \"0\",\n" +
                "\"registerAdd\": \"北京市怀柔区渤海镇怀沙路536号\",\n" +
                "\"operationsAdd\": \"北京市朝阳区酒仙桥北路9号恒通国际创新园C9A座3101室\",\n" +
                "\"contracts\": \"王浩\",\n" +
                "\"contractsPhone\": \"010-59312345\",\n" +
                "\"contractsEmail\": \"wanghao@lavatechdevelop.com\",\n" +
                "\"contractsCard\": \"110106198205021530\"\n" +
                "},\n" +
                "\"serviceInfo\": {\n" +
                "\"taxOrgId\": \"11102270000\",\n" +
                "\"signatureStatus\": \"1\",\n" +
                "\"taxDiscType\": \"1\",\n" +
                "\"certEmail\": \"552446882@qq.com\",\n" +
                "\"providerName\": \"大象服务商\"\n" +
                "}\n" +
                "}\n" +
                "}";
	    ObjectMapper objectMapper=new ObjectMapper();
        try {
            Data data = objectMapper.readValue(json, Data.class);
            System.out.println(data.getEnterprise().getEnterpriseBase().getAreaId());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
