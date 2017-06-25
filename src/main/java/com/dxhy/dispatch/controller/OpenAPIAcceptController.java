package com.dxhy.dispatch.controller;

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

import java.util.HashMap;
import java.util.Map;


/**
 * 接受openAPI传递数据
 * Created by lzy on 2017/6/25.
 */
@RestController
@RequestMapping(value = "/openApi")
public class OpenAPIAcceptController {

    private static Logger logger = LoggerFactory.getLogger(OpenAPIAcceptController.class);

    private String LOGGER_MSG = "接受openAPI传递数据";

    @Autowired
    private ObjectMapper objectMapper;


    @RequestMapping(value = "/accept", method = {RequestMethod.POST})
    public String acceptData(String json) {
        Map<String,Object> retrunMap= new HashMap<>();
        String returnJson="";
        if (logger.isInfoEnabled())
	  logger.info("{},传递的密文为:{}", LOGGER_MSG, json);
        if (StringUtils.isNotBlank(json)) {
	  byte[] decodeBase64 = Base64.decodeBase64(json);
	  String decodeJson = new String(decodeBase64);
	  if (logger.isInfoEnabled())
	      logger.info("{},密文解密后的明文为:{}", LOGGER_MSG, decodeJson);
	  retrunMap.put("returnCode","0000");
	  retrunMap.put("returnMessage","接受推送数据成功");
	  returnJson=transformationJson(retrunMap);

        }else{
	  retrunMap.put("returnCode","9999");
	  retrunMap.put("returnMessage","接受推送数据失败");
	  returnJson=transformationJson(retrunMap);
        }

        return returnJson;
    }

    public String transformationJson(Map<String,Object> retrunMap){

        try {
	  String value = objectMapper.writeValueAsString(retrunMap);
	  return  value;
        } catch (JsonProcessingException e) {
	  if (logger.isErrorEnabled())
	      logger.error("{},数据转换JSON格式错误，错误信息为:{}",LOGGER_MSG,e.getMessage());
	  e.printStackTrace();
        }
        return "";
    }

}
