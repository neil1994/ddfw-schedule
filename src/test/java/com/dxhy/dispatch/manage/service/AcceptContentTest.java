package com.dxhy.dispatch.manage.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lzy on 2017/6/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AcceptContentTest {
    private  static Logger logger= LoggerFactory.getLogger(AcceptContentTest.class);

    @Autowired
    private AcceptContentService acceptContentService;
    @Test
    @Rollback
    public void testProviderCookie(){
        logger.info("测试AcceptContentService开始执行");
        Boolean saveEnterpriseContent = acceptContentService.saveEnterpriseContent("21213213-12931-cccnlscndxcncxzcnxkcdsjdsnfldsfds");
        logger.info("测试AcceptContentService结束执行，执行结果为:{}",saveEnterpriseContent);
    }

}
