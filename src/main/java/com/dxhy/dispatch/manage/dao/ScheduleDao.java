package com.dxhy.dispatch.manage.dao;


import com.dxhy.dispatch.manage.bean.tables.EleProcessLogs;
import org.apache.ibatis.annotations.Mapper;

/**
 * 调度服务-操作的数据库中的表为ele_process_logs
 * Created by 赵睿 on 2016/11/17.
 */
@Mapper
public interface ScheduleDao {
    /**
     * 插入处理过程日志
     * @param eleProcessLogs 日志协议bean
     * @return 如果成功插入，返回true
     */
    boolean installProcessLog(EleProcessLogs eleProcessLogs);

    /**
     * 修改处理过程日志
     * @param eleProcessLogs 日志协议bean
     * @return 如果成功修改，返回true
     */
    boolean updateProcessLog(EleProcessLogs eleProcessLogs);

    /**
     * 修改企业信息，其中修改为企业id和服务商id
     * @param eleProcessLogs 日志协议bean
     * @return 如果成功修改，返回true
     */
    boolean updateProcessLogEnterpriseMsg(EleProcessLogs eleProcessLogs);

    /**
     * 根据税号查询处理过程日志所有信息
     * @param ratepayers_code 税号
     * @return  日志协议bean
     */
    EleProcessLogs selectEleProcessLogs(String ratepayers_code);

    /**
     * 根据日志主键查询处理过程日志所有信息
     * @param pk_process_logs 日志主键
     * @return  日志协议bean
     */
    EleProcessLogs selectEleProcessLogsById(String pk_process_logs);

}
