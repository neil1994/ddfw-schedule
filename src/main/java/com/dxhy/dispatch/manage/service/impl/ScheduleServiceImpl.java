package com.dxhy.dispatch.manage.service.impl;

import com.dxhy.dispatch.manage.bean.protocol.Enterprise;
import com.dxhy.dispatch.manage.bean.protocol.EnterpriseBase;
import com.dxhy.dispatch.manage.bean.protocol.ServiceInfo;
import com.dxhy.dispatch.manage.bean.tables.EleProcessLogs;
import com.dxhy.dispatch.manage.bean.tables.HandlerData;
import com.dxhy.dispatch.manage.dao.OpenApiDao;
import com.dxhy.dispatch.manage.dao.ScheduleDao;
import com.dxhy.dispatch.manage.service.HandlerService;
import com.dxhy.dispatch.manage.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dxhy.dispatch.manage.enumConfig.HandlerEnum.StartHandler;


/**
 * Created by 赵睿 on 2016/11/15.
 */
@SuppressWarnings("ALL")
public class ScheduleServiceImpl implements ScheduleService {
    private static Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private HandlerService enterpriseApplyForServiceHandler;
    @Autowired
    private HandlerService enterpriseCheckHandler;
    @Autowired
    private HandlerService enterpriseLoginHandler;
    @Autowired
    private HandlerService enterpriseRegisterHandler;
    @Autowired
    private HandlerService providerApproveEnterpriseHandler;
    @Autowired
    private HandlerService pushEnterpriseMsgToProviderHandler;
    @Autowired
    private HandlerService makeCaAndSignatureHandler;


    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private OpenApiDao openApiDao;

    @Value("${provider_id}")
    private String provider_id;

    private List<String> whiteLists;
    @Override
    public void schedule(String serialNum, String json) {
        Enterprise enterprise = parseJsom(json);
        whiteLists=getWhiteLists();
        ServiceInfo serviceInfo = enterprise.getServiceInfo();
        if (null != enterprise.getServiceInfo()) {
        	logger.debug("处理解析后的签章状态，初始化为:0");
        	serviceInfo.setSignatureStatus("0");
			logger.debug("处理解析后的金税盘类型，初始化为:1");
			serviceInfo.setTaxDiscType("1");
			enterprise.setServiceInfo(serviceInfo);
		}else{
			logger.error("企业需要的服务的相关信息ServiceInfo为Null");
		}
        
        logger.debug("开始处理流水号为{}的数据", serialNum);
        //TODO 在处理数据的时候，完全需要先去数据库中读取数据，然后判断数据库信息，最终跳过某些步骤
        EnterpriseBase enterpriseBase = enterprise.getEnterpriseBase();
        EleProcessLogs eleProcessLogs=scheduleDao.selectEleProcessLogs(enterpriseBase.getRatepayersCode());
        if(eleProcessLogs==null){
            eleProcessLogs = new EleProcessLogs(provider_id, "企业id当前未生成！", enterpriseBase.getRatepayersCode(), StartHandler.getCode()+"0",
                    StartHandler.getDescribe(), "1", "0");
            logger.info("插入调度记录表，记录为开始处理业务");
            if(scheduleDao.installProcessLog(eleProcessLogs)){
                Map<String,String> map=new HashMap<>();
                map.put("dataExchangeId",serialNum);
                map.put("processStatus","2");
                openApiDao.updateProcessStatus(map);
            }else{
                logger.error("插入调度记录表失败，失败数据为{},对该条数据不做任何处理，请求内层数据为：{}", eleProcessLogs, json);
            }
        }
        HandlerData handlerData = new HandlerData(enterprise, serialNum);
        handlerData.setEleProcessLogs(eleProcessLogs);
        scheduleHandler(handlerData);
    }

    /**
     * 解析传过来的json数据
     * @param json
     * @return
     */
    private Enterprise parseJsom(String json) {
        logger.info("json内层报文解析");
        logger.debug("需要解析的json报文为：{}", json);
        Enterprise enterprise=new Enterprise();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            enterprise=objectMapper.readValue(json,Enterprise.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enterprise;
    }


    /**
     * 调度，组装需要的服务
     *
     * @param handlerData
     */
    protected void scheduleHandler(HandlerData handlerData) {
        List<HandlerService> handlerServiceList = new ArrayList<>();
        handlerServiceList.add(enterpriseCheckHandler);
        handlerServiceList.add(enterpriseLoginHandler);
        handlerServiceList.add(enterpriseRegisterHandler);
        handlerServiceList.add(enterpriseApplyForServiceHandler);
        handlerServiceList.add(providerApproveEnterpriseHandler);
        handlerServiceList.add(makeCaAndSignatureHandler);
        handlerServiceList.add(pushEnterpriseMsgToProviderHandler);

        for (int i = 0; i < handlerServiceList.size() - 1; i++) {
            handlerServiceList.get(i).setNextHandler(handlerServiceList.get(i + 1));
        }
        handlerServiceList.get(0).handlerService(handlerData);
    }

    /**
     * 获取白名单
     * @return
     */
    public List<String> getWhiteLists() {
        if(whiteLists.isEmpty()){
            whiteLists.add("10");
        }
        return whiteLists;
    }
}
