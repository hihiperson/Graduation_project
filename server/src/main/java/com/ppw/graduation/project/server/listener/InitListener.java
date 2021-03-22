package com.ppw.graduation.project.server.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/14 21:27
 */

//定时运行或需要初始化的类
@Component
public class InitListener {

    private static final Logger log = LoggerFactory.getLogger(InitListener.class);

    //@Scheduled(cron = "0/10 * * * * ?")
    public void test(){
        //log.info("-----------测试定时任务------");
    }
}
