package com.dxhy.dispatch.manage.dao;

import com.dxhy.dispatch.DdfwScheduleApplication;
import com.dxhy.dispatch.manage.bean.tables.ApiLogContent;
import com.dxhy.dispatch.utils.DistributedKeyMaker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User : zhiyong.li
 * <p> OpenApiDaoTest 数据库连接测试
 * created time : 2017-06-2017/6/22,20:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdfwScheduleApplication.class)
@Rollback
public class ApiLogContentMapperTest {

    private static Logger logger= LoggerFactory.getLogger(ApiLogContentMapperTest.class);

    @Autowired
    private  ApiLogContentMapper apiLogContentMapper;
    @Test
    public void testGetContent(){
        logger.info("测试APILog插入数据开始");
        ApiLogContent apiLogContent= getApiLogContent();
        int insertContent = apiLogContentMapper.insertContent(apiLogContent);
        logger.info("测试APILog插入数据结束，测试结果为:{}",insertContent>0?true:false);

    }

    private ApiLogContent getApiLogContent() {
        ApiLogContent apiLogContent=new ApiLogContent();
        apiLogContent.setContent("ddsdjsldsjdls死定了斯柯达水利水电是代理");
        apiLogContent.setPk_log_content(DistributedKeyMaker.getInstance().generateShotKey());
        apiLogContent.setLog_id(DistributedKeyMaker.getInstance().generateShotKey());
        return apiLogContent;
    }


}
