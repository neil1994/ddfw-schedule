package com.dxhy.dispatch.manage.dao;

import com.dxhy.dispatch.DdfwScheduleApplication;
import com.dxhy.dispatch.manage.bean.tables.EleProcessLogs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User : zhiyong.li
 * <p>
 * created time : 2017-06-2017/6/22,20:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdfwScheduleApplication.class)
@Rollback
public class ScheduleDaoTest {

    @Autowired
    private ScheduleDao scheduleDao;
    @Test
    public void selectEleProcessLogsByIdTest(){
        EleProcessLogs eleProcessLogs = scheduleDao.selectEleProcessLogsById("868041518823374848");
        System.out.println(eleProcessLogs.toString());
    }
}
