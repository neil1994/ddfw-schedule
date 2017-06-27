package com.dxhy.dispatch.manage.dao;

import com.dxhy.dispatch.DdfwScheduleApplication;
import com.dxhy.dispatch.manage.bean.tables.ApiLog;
import com.dxhy.dispatch.utils.DistributedKeyMaker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * User : zhiyong.li
 * <p> OpenApiDaoTest 数据库连接测试
 * created time : 2017-06-2017/6/22,20:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdfwScheduleApplication.class)
public class ApiLogMapperTest {

    private static Logger logger= LoggerFactory.getLogger(ApiLogMapperTest.class);

    @Autowired
    private  ApiLogMapper apiLogMapper;
    @Test
    @Rollback
    public void testSelectByPrimaryKey(){
        logger.info("测试APILog插入数据开始");
        ApiLog apiLog = apiLogMapper.selectByPrimaryKey("879278212499243008");
        logger.info("测试APILog插入数据结束，测试结果为:{}",apiLog.toString());

    }
    @Test
    public void testInsertSelective(){
        logger.info("测试APILog插入数据，使用insertSelective开始");
        ApiLog apiLog= getApiLog();
        int insert = apiLogMapper.insertApiLog(apiLog);
        logger.info("测试APILog插入数据结束，使用insertSelective测试结果为:{}",insert>0?true:false);

    }
    @Test
    public void testUpdateByPrimaryKeySelective(){
        logger.info("测试APILog修改数据，使用updateByPrimaryKeySelective开始");
        ApiLog apiLog= getUpdateApiLog();
        int insert = apiLogMapper.updateByApiLog(apiLog);
        logger.info("测试APILog修改数据结束，使用updateByPrimaryKeySelective测试结果为:{}",insert>0?true:false);

    }

    private ApiLog getUpdateApiLog() {
        ApiLog apiLog=new ApiLog();
        apiLog.setPk_log("879278212499243008");
        apiLog.setProcess_status("1");
        return apiLog;
    }


    private ApiLog getApiLog() {
        ApiLog apiLog=new ApiLog(DistributedKeyMaker.getInstance().generateShotKey(),"02","0",new Date());
        return apiLog;
    }




}
