package com.ppw.graduation.project.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.model.entity.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/25 19:32
 */

//RabbitMQ生产者
@Service
public class RabbitService {

    private static final Logger log = LoggerFactory.getLogger(RabbitTemplate.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    //将日志充当消费塞到 mq-server里去（topic搜索：exchange+routingKey）
    public void mqSendLog(SysLog sysLog){
        rabbitTemplate.setExchange(env.getProperty("rabbitmq.log.exchange"));
        rabbitTemplate.setRoutingKey(env.getProperty("rabbitmq.log.routing.key"));

        try {
            Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(sysLog))
                    //设置持久化
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            rabbitTemplate.send(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}























