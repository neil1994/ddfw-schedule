package com.dxhy.dispatch.manage.task;

import com.dxhy.dispatch.manage.dao.OpenApiDao;
import com.dxhy.dispatch.manage.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述
 * Created by 赵睿 on 2016/11/15.
 */
@Component
@EnableScheduling
public class GetNewEnterpriseTask {

    private static final Logger logger= LoggerFactory.getLogger(GetNewEnterpriseTask.class);

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private OpenApiDao openApiDao;

    @Scheduled(cron = "0 */1 *  * * * ")
    public void scanTask(){
        logger.info("查询京东服务商企业信息注册认证定时器启动");
        Map<String,String> requestMap= new HashMap<>();
        requestMap.put("appId","02");
        requestMap.put("processStatus","0");
        List<Map<String,String>> lists=openApiDao.getContent(requestMap);

        logger.info("查询数据库，获得接口中存储的待处理的数据，此批需要处理的数据有{}条",lists.size());

        for (Map<String,String> map: lists) {
            scheduleService.schedule(map.get("log_id"),map.get("content"));
            Map<String,String> mapUpdate= new HashMap<>();
            mapUpdate.put("log_id",map.get("log_id"));
            mapUpdate.put("processStatus","1");
            openApiDao.updateProcessStatus(mapUpdate);
        }
    }

}
