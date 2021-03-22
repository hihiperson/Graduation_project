package com.ppw.graduation.project.server.service;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.model.entity.RecodeUser;
import com.ppw.graduation.project.model.entity.ShowConsumables;
import com.ppw.graduation.project.model.mapper.RecodeUserMapper;
import com.ppw.graduation.project.model.mapper.ShowConsumablesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/15 22:55
 */

//普通用户核心业务
@Service
public class Index2Service {

    private static final Logger log = LoggerFactory.getLogger(Index2Service.class);

    @Autowired
    private RedisSrvice redisSrvice;

    @Autowired
    private RecodeUserMapper recodeUserMapper;

    @Autowired
    private ShowConsumablesMapper showConsumablesMapper;

    @Autowired
    private ObjectMapper objectMapper;

    //查询所有记录的方法
    public RecodeUser selectUserRecord(Integer uid){

        RecodeUser recodeUser = null;

        //TODO: 查询缓存，不存在走数据库
        if (uid!=null){
            if (redisSrvice.exist(uid.toString())){
                String result = redisSrvice.stringGet(uid.toString()).toString();
                log.info("-----用户id={}：从缓存中查询User记录-----", uid);
                if (StrUtil.isNotBlank(result)){
                    try {
                        //objectmapper帮我们反序列化
                        recodeUser = objectMapper.readValue(result, RecodeUser.class);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }else {
                //TODO:缓存中没key(数据)时走数据库
                log.info("-----用户id={}：从数据库中查询User记录-----", uid);
                recodeUser = recodeUserMapper.selectByUserId(uid);
                if (recodeUser!=null){
                    try {
                        redisSrvice.stringSet(uid.toString(), objectMapper.writeValueAsString(recodeUser));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return recodeUser;
    }

    public List<ShowConsumables> selectShowConsumablesList(){
        return showConsumablesMapper.selectShowConsumablesList();
    }

}















