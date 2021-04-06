package com.ppw.graduation.project.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.model.entity.SysLog;
import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.server.enums.Dynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/14 22:38
 */

//一些公共方法
//TODO：发送日志记录的方法

@Service
public class CommonService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitService rabbitService;

    //创建一个日志内存
    SysLog sysLog = new SysLog();

    //TODO：往数据库插入日志
    public void addLog(String uname, String operate, String method, Object param, String memo){
        try {
            //TODO: 插入数据库日志
            sysLog.setIp(Dynamic.ip);
            sysLog.setTime(Dynamic.useTime);
            sysLog.setUsername(uname);
            sysLog.setOperation(operate);
            sysLog.setMethod(method);
            sysLog.setMemo(memo);
            sysLog.setParams(objectMapper.writeValueAsString(param));
        } catch (JsonProcessingException e) {
            sysLog.setMemo(e.getMessage());
            e.printStackTrace();
        }
        rabbitService.mqSendLog(sysLog);
    }


}
