package com.ppw.graduation.project.server.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/24 15:54
 */

//自定义注入redisson的操作bean组件
@Configuration
public class RedissonConfig {

    @Autowired
    private Environment env;

    //TODO: 单节点配置
    @Bean
    public RedissonClient client(){
        Config config = new Config();
        config.useSingleServer().setAddress(env.getProperty("redisson.url.single"));
        RedissonClient redissonClient = Redisson.create();
        return redissonClient;
    }

    //集群
/*    @Bean
    public RedissonClient client(){
        Config config = new Config();
        config.useClusterServers().setScanInterval(2000)
                .addNodeAddress(StringUtils.split(env.getProperty("redisson.url.single"), ","))
                .setMasterConnectionMinimumIdleSize(10)
                .setMasterConnectionPoolSize(64)
                .setSlaveConnectionMinimumIdleSize(10)
                .setSlaveConnectionPoolSize(64)
                .setConnectTimeout(15000);
        RedissonClient client = Redisson.create(config);
        return client;
    }*/
}
