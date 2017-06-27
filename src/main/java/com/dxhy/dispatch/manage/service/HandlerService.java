package com.dxhy.dispatch.manage.service;

import com.dxhy.dispatch.manage.bean.tables.EleProcessLogs;
import com.dxhy.dispatch.manage.bean.tables.HandlerData;
import com.dxhy.dispatch.manage.constants.ProviderMsg;
import com.dxhy.dispatch.manage.constants.UrlConstants;
import com.dxhy.dispatch.manage.dao.ScheduleDao;
import com.dxhy.dispatch.manage.enumConfig.HandlerEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by 赵睿 on 2016/11/15.
 *
 * 操作-模版
        */
public abstract class HandlerService {
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected UrlConstants urlConstants;
    @Autowired
    protected ProviderMsg providerMsg;

    private static final Logger logger= LoggerFactory.getLogger(HandlerService.class);

    /**
     * 下一个需要处理的操作者
     */
    private HandlerService nextHandlerService;

    /**
     * 操作名称
     */
    protected HandlerEnum handlerEnum;

    @Autowired
    protected ScheduleDao scheduleDao;

    /**
     * 对业务的操作
     */
    public void handlerService(HandlerData handlerData){
        setHandlerEnum();
        EleProcessLogs eleProcessLogs=handlerData.getEleProcessLogs();
        if(isAlways()){
            dealHandler(handlerData, eleProcessLogs);
        }else{
            int processCode=Integer.parseInt(eleProcessLogs.getProcess_code().substring(0,2));
            if(processCode>handlerEnum.getCode()){
                if(nextHandlerService!=null){
                    nextHandlerService.handlerService(handlerData);
                }
            }else{
                eleProcessLogs.updateEleProcessLogs(handlerEnum.getCode()+"0",handlerEnum.getDescribe(),"1","0");
                dealHandler(handlerData, eleProcessLogs);
            }
        }
    }

    /**
     * 处理当前服务
     * @param handlerData 处理数据
     * @param eleProcessLogs 处理结果数据
     */
    private void dealHandler(HandlerData handlerData, EleProcessLogs eleProcessLogs) {
        logger.info("update调度记录表，现执行到{}",handlerEnum.getDescribe());
        try {
            if(handler(handlerData)){
                logger.debug("update调度记录表，记录状态为成功");
                scheduleDao.updateProcessLog(eleProcessLogs);
                if(nextHandlerService!=null){
                    nextHandlerService.handlerService(handlerData);
                }
            }else{
                logger.info("update调度记录表，记录状态为失败");
                eleProcessLogs.setProcess_status("0");
                scheduleDao.updateProcessLog(eleProcessLogs);
            }
        } catch (IOException e) {
            logger.error("update调度记录表，记录状态为失败",e);
            eleProcessLogs.setProcess_status("0");
            eleProcessLogs.setProcess_msg(eleProcessLogs.getProcess_msg()+"--调度失败--->失败原因为："+e.getMessage());
            scheduleDao.updateProcessLog(eleProcessLogs);
        }
    }

    /**
     * 配置操作名称
     */
    protected void setHandlerEnum(){
        String className=this.getClass().getSimpleName();
        this.handlerEnum=HandlerEnum.valueOf(className);
    }

    /**
     * 设置下一个处理者
     * @param handlerService 调度服务的具体服务
     */
    public void setNextHandler(HandlerService handlerService){
        this.nextHandlerService=handlerService;
    }

    /**
     * 具体操作，
     * @return 成功返回true
     */
    protected abstract boolean handler(HandlerData handlerData) throws IOException;

    protected boolean isAlways(){
        return false;
    }


}
