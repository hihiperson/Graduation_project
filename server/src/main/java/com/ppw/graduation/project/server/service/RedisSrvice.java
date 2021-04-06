package com.ppw.graduation.project.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.server.enums.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/15 23:24
 */

//Redis的一些公共配置
@Service
public class RedisSrvice {

    private static final Logger log = LoggerFactory.getLogger(Index2Service.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    //可能会使用很多次，直接提取出来
    private ValueOperations opsForValue(){
        return stringRedisTemplate.opsForValue();
    }
    private ListOperations listOperations(){
        return redisTemplate.opsForList();
    }
    private HashOperations hashOperations(){return redisTemplate.opsForHash();}

    //TODO: 添加String类型的数据（record_usr）
    public void stringSet(final String key, final String value){
        opsForValue().set(Constant.SelectRecordUserKey+key, value);
    }
    //取出String类型的数据
    public Object stringGet(final String key){
        return opsForValue().get(Constant.SelectRecordUserKey+key);
    }

    //TODO: stringTemplate的内置验证函数——检验key是否存在
    public Boolean exist(final String key){
        return stringRedisTemplate.hasKey(Constant.SelectRecordUserKey+key);
    }



    //TODO: List类型
    public Boolean existList(final String key){
        if (listOperations().size(key)>0){
            return true;
        }else {
            return false;
        }
    }


    //TODO: Hash类型
    public Boolean existHash(final String key, final String id){
        if (hashOperations().size(key)>0){
            return hashOperations().hasKey(key, id);
        }else {
            return false;
        }
    }
}





















