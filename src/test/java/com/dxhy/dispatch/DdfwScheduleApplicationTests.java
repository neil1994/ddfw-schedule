package com.dxhy.dispatch;

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

		ObjectMapper objectMapper=new ObjectMapper();
		try {
            objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,true);
            String valueAsString = objectMapper.writeValueAsString(enterprise);
            System.out.println(valueAsString);
        } catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
