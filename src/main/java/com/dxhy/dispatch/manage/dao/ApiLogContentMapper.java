package com.dxhy.dispatch.manage.dao;

import com.dxhy.dispatch.manage.bean.tables.ApiLogContent;

/**
 * ApiLogContentMapper接口
 */
public interface ApiLogContentMapper {

    /**
     * 插入 ApiLogContent 内容
     * @param record
     * @return
     */
    int insertContent(ApiLogContent record);
}