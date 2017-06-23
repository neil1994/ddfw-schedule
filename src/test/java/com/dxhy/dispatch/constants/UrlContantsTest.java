package com.dxhy.dispatch.constants;

import com.dxhy.dispatch.DdfwScheduleApplication;
import com.dxhy.dispatch.manage.constants.UrlConstants;
import com.dxhy.dispatch.manage.service.HandlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User : zhiyong.li
 * <p>
 * created time : 2017-06-2017/6/23,11:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdfwScheduleApplication.class)
public class UrlContantsTest {
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(HandlerService.class);
    @Autowired
    private UrlConstants urlConstants;
    @Test
    public void testGetUrlPasrm(){
        logger.info("测试UrlConstants工具类获取配置数据");
        String captcha = urlConstants.getCaptcha();
        logger.info("UrlConstants工具类获取配置数据,captcha为:{}",captcha);


    }

}
