package com.dxhy.dispatch.manage.dao;

import com.dxhy.dispatch.DdfwScheduleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User : zhiyong.li
 * <p> OpenApiDaoTest 数据库连接测试
 * created time : 2017-06-2017/6/22,20:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdfwScheduleApplication.class)
@Rollback
public class OpenApiDaoTest {

    @Autowired
    private  OpenApiDao openApiDao;
    @Test
    public void testGetContent(){
        Map<String,String> map= new HashMap<>();
        map.put("appId","01");
        map.put("processStatus","1");
        List<Map<String, String>> content = openApiDao.getContent(map);
        System.out.println(content.toString());
    }
    @Test
    public void testUpdateProcessStatus(){
        Map<String,String> map= new HashMap<>();
        map.put("dataExchangeId","jd00000120170526936488624");
        map.put("processStatus","3");
        boolean updateProcessStatus = openApiDao.updateProcessStatus(map);
        System.out.println(updateProcessStatus);
        if (updateProcessStatus)
            System.out.println("修改成功！");
    }


}
