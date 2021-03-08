package com.ppw.graduation.project.server.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.model.entity.SysLog;
import com.ppw.graduation.project.server.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/25 19:31
 */

//RabbitMQ-监听机制
@Component
public class RabbitmqListener {

    private static final Logger log = LoggerFactory.getLogger(RabbitmqListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LogService logService;

    @RabbitListener(queues = {"${rabbitmq.log.queue}"}, containerFactory = "singleListenerContainer")
    public void consumeLogMsg(@Payload byte[] msg){
        try {
            log.info("--Rabbit日志记录的监听-消费者-监听到消息--");
            SysLog sysLog = objectMapper.readValue(msg, SysLog.class);
            logService.recordLog(sysLog);

        }catch (Exception e){
            log.error("rabbit---日志监听记录---发生异常:{}", e.fillInStackTrace());
        }
    }
}
























