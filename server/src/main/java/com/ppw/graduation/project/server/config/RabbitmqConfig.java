package com.ppw.graduation.project.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/25 16:22
 */

//rabbitmq  模板操作bean组件实例的自定义注入配置
@Configuration
public class RabbitmqConfig {

    private final static Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    //TODO：预先创建交换机，路由机器绑定
    @Bean
    public TopicExchange logExchange() {
        return new TopicExchange(env.getProperty("rabbitmq.log.exchange"), true, false);
    }

    @Bean
    public Queue logQueue() {
        return new Queue(env.getProperty("rabbitmq.log.queue"), true);
    }

    //进行绑定
    @Bean
    public Binding logBinding() {
        return BindingBuilder.bind(logQueue()).to(logExchange()).with(env.getProperty("rabbitmq.log.routing.key"));
    }

    //TODO: rabbitmq自定义注入模板操作
    @Bean
    public RabbitTemplate rabbitTemplate() {
        //使用消息确认模式，避免消息丢失或投递失败
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                log.info("消息发送成功：correlationData={}, ack={}, s={}", correlationData, ack, s);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("消息丢失：message={}, replyCode={}, replyText={}, exchange={}, routingKey={}", message, i, s, s1, s2);
            }
        });
        return rabbitTemplate;
    }

    //TODO：单一实例消费者
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        return factory;
    }

    //多实例消费者
//    @Bean("multiListenerContainer")
//    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factoryConfigurer.configure(factory, connectionFactory);
//        // factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        //确认消费模式-NONE
//        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
//        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency", int.class));
//        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency", int.class));
//        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch", int.class));
//        return factory;
//    }
}