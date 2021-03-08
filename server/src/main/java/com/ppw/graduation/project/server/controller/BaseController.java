package com.ppw.graduation.project.server.controller;


import com.ppw.graduation.project.api.response.BaseResponse;
import com.ppw.graduation.project.api.response.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/21 21:43
 */

//测试统一响应接口
//测试日志
@RestController
@RequestMapping("base")
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class) ;

    @RequestMapping("/info")
    public BaseResponse info(String name){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            log.info("-----毕设的log测试！-------");
            //TODO: 写真正的核心业务逻辑
            if (StringUtils.isNotBlank(name)){
                name = "毕设框架搭建~";
            }
            response.setData(name);
        }catch (Exception e){
            response = new BaseResponse(StatusCode.Fail, e.fillInStackTrace());
        }
        return response;
    }
}
