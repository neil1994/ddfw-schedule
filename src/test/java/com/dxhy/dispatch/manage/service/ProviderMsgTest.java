package com.dxhy.dispatch.manage.service;

import com.dxhy.dispatch.manage.constants.ProviderMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by lzy on 2017/6/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderMsgTest {
    private  static Logger logger= LoggerFactory.getLogger(ProviderMsgTest.class);
    @Autowired
    private ProviderMsg providerMsg;

    @Test
    public void testProviderCookie(){
        logger.info("开始测试ProviderMsg");
        try {
	  String providerCookie = providerMsg.getProviderCookie();
	  logger.info("获取的providerCookie为：{}",providerCookie);
	  String providerId = providerMsg.getProviderId();
	  logger.info("获取的providerId为：{}",providerId);
	  String providerProductId = providerMsg.getProviderProductId();
	  logger.info("获取的providerProductId为：{}",providerProductId);
        } catch (IOException e) {
	  e.printStackTrace();
        }
    }

}
