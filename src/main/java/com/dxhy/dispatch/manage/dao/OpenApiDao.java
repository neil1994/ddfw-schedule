package com.dxhy.dispatch.manage.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 对openApi的数据库的操作
 * Created by 赵睿 on 2016/11/17.
 */
@Mapper
public interface OpenApiDao {
    /**
     * 获得需要解析的内层报文数据
     * @return 包含了内层报文和流水号
     */
    List<Map<String,String>> getContent(Map<String,String> map);

    /**
     * 根据流水号修改操作状态
     * @param map 流水号
     * @return 是否修改成功
     */
    boolean updateProcessStatus(Map<String,String> map);
}
