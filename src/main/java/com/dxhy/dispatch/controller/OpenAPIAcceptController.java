package com.dxhy.dispatch.controller;

import com.dxhy.dispatch.manage.constants.SystemConstants;
import com.dxhy.dispatch.manage.service.AcceptContentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * 接受openAPI传递数据
 * Created by lzy on 2017/6/25.
 */
@RestController
@RequestMapping(value = "/ddfw-schedule/openApi")
public class OpenAPIAcceptController {

    private static Logger logger = LoggerFactory.getLogger(OpenAPIAcceptController.class);

    private String LOGGER_MSG = "接受openAPI传递数据";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AcceptContentService acceptContentService;

    /**
     * 接受openAPI传递数据实现方法
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/accept", method = {RequestMethod.POST})
    public String acceptData(String json) {
        Map<String, Object> retrunMap = new HashMap<>();
        if (logger.isInfoEnabled())
            logger.info("{},传递的密文为:{}", LOGGER_MSG, json);
        if (StringUtils.isNotBlank(json)) {
            byte[] decodeBase64 = Base64.decodeBase64(json);
            String decodeJson = getDecodeString(decodeBase64);
            if (StringUtils.isNotBlank(decodeJson)) {
                Boolean saveEnterpriseContent = acceptContentService.saveEnterpriseContent(decodeJson);
                if (logger.isInfoEnabled())
                    logger.info("{},密文解密后的明文为:{}", LOGGER_MSG, decodeJson);
                if (saveEnterpriseContent) {
                    retrunMap.put("returnCode", "0000");
                    retrunMap.put("returnMessage", "接受推送数据成功");
                }
            }
        } else {
            retrunMap.put("returnCode", "9999");
            retrunMap.put("returnMessage", "传入参数为空！");
        }

        return transformationJson(retrunMap);
    }

    /**
     * byte数据转换String
     *
     * @param decodeBase64
     * @return
     */
    private String getDecodeString(byte[] decodeBase64) {
        String decodeIson = "";
        try {
            decodeIson = new String(decodeBase64, SystemConstants.charset);
        } catch (UnsupportedEncodingException e) {
            logger.error("{},byte数据转换String错误，错误信息为:{}", LOGGER_MSG, e.getMessage());
            e.printStackTrace();
        }
        return decodeIson;
    }

    /**
     * -
     * Map to Json
     *
     * @param retrunMap
     * @return
     */
    public String transformationJson(Map<String, Object> retrunMap) {

        try {
            String value = objectMapper.writeValueAsString(retrunMap);
            return value;
        } catch (JsonProcessingException e) {
            if (logger.isErrorEnabled())
                logger.error("{},数据转换JSON格式错误，错误信息为:{}", LOGGER_MSG, e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

}
