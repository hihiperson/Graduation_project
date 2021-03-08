package com.ppw.graduation.project.server.service;

import com.ppw.graduation.project.model.entity.SysLog;
import com.ppw.graduation.project.model.mapper.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/25 20:00
 */

//记录日志
@Service
public class LogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    //记录日志——插入数据库
    public void recordLog(SysLog sysLog){
        sysLogMapper.insertSelective(sysLog);
    }
}
