package com.ppw.graduation.project.server.service;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.model.entity.ApplyCons;
import com.ppw.graduation.project.model.entity.Consumables;

import com.ppw.graduation.project.model.mapper.ApplyConsMapper;
import com.ppw.graduation.project.model.mapper.ConsumablesMapper;
import com.ppw.graduation.project.server.enums.Constant;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.Date;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/23 22:11
 */

//TODO: 有关耗材的业务
@Service
public class ConsService {
    private static final Logger log = LoggerFactory.getLogger(Index2Service.class);

    @Autowired
    private ConsumablesMapper consMapper;

    @Autowired
    private ApplyConsMapper applyConsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisSrvice redisSrvice;

    //根据cid查询详情
    //TODO:查缓存，缓存没有查数据库
    public Consumables selectByPrimaryKey(Integer cid){
        String key = Constant.SelectConsumablesByCid;
        HashOperations<String, String, Consumables> hashOperations = redisTemplate.opsForHash();
        if (cid!=null){
            if (redisSrvice.existHash(key, cid+"")){
                Consumables consumables = hashOperations.get(Constant.SelectConsumablesByCid, cid+"");
                log.info("-----用户cid={}：从Hash缓存中查询consumables记录-----", cid);
                if (consumables != null){
                    return consumables;
                }
            }else {
                log.info("-----用户cid={}：从数据库中查询consumables记录-----", cid);
                Consumables consumables = consMapper.selectByPrimaryKey(cid);
                if (consumables!=null){
                    hashOperations.put(key, cid+"", consumables);
                }
                return consumables;
            }
        }
        return null;
    }

    //TODO: 预约设备
    public int applyCons(ApplyCons applyCons){
        applyCons.setCreateTime(DateTime.now().toDate());
        applyCons.setKeyword(applyCons.getCid()+" "+applyCons.getApplyDay()+" "+applyCons.getApplyNum()+" "+applyCons.getApplyReson());
        System.out.println(applyCons.toString());
        return applyConsMapper.insertSelective(applyCons);
    }

}
