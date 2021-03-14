package com.ppw.graduation.project.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.api.response.BaseResponse;
import com.ppw.graduation.project.api.response.StatusCode;
import com.ppw.graduation.project.model.entity.SysLog;
import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.model.mapper.UserMapper;
import com.ppw.graduation.project.server.config.FilterConfig;
import com.ppw.graduation.project.server.enums.Constant;
import com.ppw.graduation.project.server.enums.Dynamic;
import com.ppw.graduation.project.server.service.RabbitService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/25 17:13
 */

//日志记录——测试而已
@RestController
@RequestMapping("/log")
public class LogController {

    public static final Logger log = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitService rabbitService;


    @RequestMapping("/test")
    public BaseResponse addUser(@RequestBody User user){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //TODO: 写真正的核心业务逻辑
            Long time1 = Dynamic.useTime;
            int res = userMapper.insertSelective(user);
            if (res > 0){
                String uname = "达令";
                String operation = "明天溜了";
                String method = "addUser";
                String params = objectMapper.writeValueAsString(user);
                Long time = Dynamic.useTime;
                //FilterConfig.filterRegistrationBean("/*");
                String ip = Dynamic.ip;
                System.out.println("========ip==="+ip);
                System.out.println("========time==="+time);
                Date create_date = DateTime.now().toDate();
                String memo = null;
                SysLog sysLog = new SysLog(uname, operation, method, params, time, ip, create_date, memo);
                rabbitService.mqSendLog(sysLog);
            }else {
                log.error("-----------用户添加失败--------------");
            }
        }catch (Exception e){
            response = new BaseResponse(StatusCode.MQERROR);
            response.setData(e.fillInStackTrace());
        }
        return response;
    }
}