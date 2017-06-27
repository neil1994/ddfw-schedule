package com.dxhy.dispatch.manage.service;

/**
 * 调度服务，代替人工完成操作
 * Created by 赵睿 on 2016/11/15.
 */
public interface ScheduleService {
    /**
     * 调度服务，代替人工完成操作
     */
    void schedule(String log_id, String json);
}
