package com.dxhy.dispatch.controller;

import com.dxhy.dispatch.manage.constants.SystemConstants;
import com.dxhy.dispatch.manage.service.AcceptContentService;
import com.dxhy.dispatch.utils.Base64Encoding;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
     * @param request
     * @return
     */
    @RequestMapping(value = "/accept", method = {RequestMethod.POST})
    public String acceptData(HttpServletRequest request) throws Exception {
        String json = IOUtils.toString(request.getInputStream(),SystemConstants.charset);
        Map<String, Object> retrunMap = new HashMap<>();
        if (logger.isInfoEnabled())
            logger.info("{},传递的密文为:{}", LOGGER_MSG, json.length());
        if (StringUtils.isNotBlank(json)) {
            String decodeJson = Base64Encoding.decodeToString(json);
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

        return objectMapper.writeValueAsString(retrunMap);
    }

}
