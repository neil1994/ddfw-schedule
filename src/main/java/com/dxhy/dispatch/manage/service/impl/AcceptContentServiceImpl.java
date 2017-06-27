package com.dxhy.dispatch.manage.service.impl;

import com.dxhy.dispatch.manage.bean.tables.ApiLog;
import com.dxhy.dispatch.manage.bean.tables.ApiLogContent;
import com.dxhy.dispatch.manage.dao.ApiLogContentMapper;
import com.dxhy.dispatch.manage.dao.ApiLogMapper;
import com.dxhy.dispatch.manage.service.AcceptContentService;
import com.dxhy.dispatch.utils.DistributedKeyMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * User : zhiyong.li
 * <p>接收企业数据服务接口实现类
 * created time : 2017-06-2017/6/26,14:07
 */
@Service
public class AcceptContentServiceImpl implements AcceptContentService{

    private static Logger logger = LoggerFactory.getLogger(AcceptContentServiceImpl.class);

    private String LOGGER_MSG="接收企业数据服务";

    @Autowired
    private ApiLogMapper apiLogMapper;
    @Autowired
    private ApiLogContentMapper apiLogContentMapper;

    @Override
    public Boolean saveEnterpriseContent(String content) {
        if (logger.isInfoEnabled())
            logger.info("{}开始执行，传入的内容长度为:{}",LOGGER_MSG,content.length());
        String pkApiLog = DistributedKeyMaker.getInstance().generateShotKey();
        if (logger.isInfoEnabled())
            logger.info("{},保存ApiLog数据开始，主键为:{}",LOGGER_MSG,pkApiLog);
        Boolean saveApiLog = saveApiLog(pkApiLog);
        if (logger.isInfoEnabled())
            logger.info("{},保存ApiLog数据结束，保存结果为:{}",LOGGER_MSG,saveApiLog);
        if (saveApiLog){
            if (logger.isInfoEnabled())
                logger.info("{},保存ApiLogContent数据开始，log主键为:{},主要内容长度为:{}",LOGGER_MSG,pkApiLog,content.length());
            Boolean saveApiLogContent = saveApiLogContent(pkApiLog, content);
            if (logger.isInfoEnabled())
                logger.info("{},保存ApiLogContent数据结束，保存结果为:{}",LOGGER_MSG,saveApiLogContent);
            if (saveApiLogContent)
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 保存ApiLogContent数据
     * @param pkApiLog
     * @param content
     * @return
     */
    private Boolean saveApiLogContent(String pkApiLog, String content) {
        ApiLogContent apiLogContent=new ApiLogContent();
        apiLogContent.setPk_log_content(DistributedKeyMaker.getInstance().generateShotKey());
        apiLogContent.setLog_id(pkApiLog);
        apiLogContent.setContent(content);
        int insertContent = apiLogContentMapper.insertContent(apiLogContent);
        if (insertContent>0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * 保存ApiLog数据
     * @param pkApiLog
     * @return
     */
    private Boolean saveApiLog(String pkApiLog) {
        ApiLog apiLog=new ApiLog(pkApiLog,"02","0",new Date());
        int insertApiLog = apiLogMapper.insertApiLog(apiLog);
        if (insertApiLog>0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
