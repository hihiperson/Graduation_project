package com.ppw.graduation.project.server.service;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.model.dto.NoReturnDTO;
import com.ppw.graduation.project.model.dto.UserRecordDTO;
import com.ppw.graduation.project.model.entity.ApplyCons;
import com.ppw.graduation.project.model.entity.RecodeUser;
import com.ppw.graduation.project.model.entity.ShowConsumables;
import com.ppw.graduation.project.model.mapper.ApplyConsMapper;
import com.ppw.graduation.project.model.mapper.RecodeUserMapper;
import com.ppw.graduation.project.model.mapper.ViewMapper;
import com.ppw.graduation.project.server.enums.Constant;
import jodd.datetime.TimeUtil;
import org.mockito.internal.util.collections.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private ViewMapper viewMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ApplyConsMapper applyConsMapper;

    private List<UserRecordDTO> list;

    //TODO:查询用户记录的方法
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

    //TODO: 查询商品列表的方法
    public List<ShowConsumables> selectShowConsumablesList(){
        return viewMapper.selectShowConsumablesList();
    }

    //TODO: 查询已通过申请的方法
    public List<UserRecordDTO> selectPassedList(Integer userId){
        ListOperations<String, UserRecordDTO> listOperations = redisTemplate.opsForList();
        if (userId!=null) {
            String key = Constant.SelectPassed + userId;
            if (redisSrvice.existList(key)) {
                log.info("-----userId={}：从List缓存中查询PassedDTO已通过记录-----", userId);
                //查询五条记录
                list = listOperations.range(key, 0, 4); //左闭，右开
                if (list != null) {
                    return list;
                }
            }else {
                //状态2是已通过
                List<UserRecordDTO> userRecordDTOS = viewMapper.selectUserRecordList(userId, 2);
                log.info("-----userId={}：从数据库查询PassedDTO已通过记录-----", userId);
                if (!ListUtils.isEmpty(userRecordDTOS)) {
                    listOperations.leftPushAll(key, userRecordDTOS);
                    if(userRecordDTOS.size()>4){
                        list = userRecordDTOS.subList(0,4);
                        return list;
                    }
                }else {
                    UserRecordDTO userRecordDTO = new UserRecordDTO();
                    userRecordDTO.setName("暂无数据……");
                    userRecordDTO.setMemo(" ");
                    userRecordDTO.setUnit(" ");
                    listOperations.leftPush(key, userRecordDTO);
                    redisTemplate.expire(key, 10L, TimeUnit.MINUTES);
                }
                return userRecordDTOS;
            }

        }
        return null;
    }

    //TODO: 查询未通过方法
    public List<UserRecordDTO> selectNoPassedList(Integer userId){
        ListOperations<String, UserRecordDTO> listOperations = redisTemplate.opsForList();
        if (userId!=null) {
            String key = Constant.SelectNoPassed + userId;
            if (redisSrvice.existList(key)) {
                log.info("-----userId={}：从List缓存中查询PassedDTO未通过记录-----", userId);
                //查询五条记录
                list = listOperations.range(key, 0, 2);
                if (list != null) {
                    return list;
                }
            }else {
                //状态3是未通过
                List<UserRecordDTO> userRecordDTOS = viewMapper.selectUserRecordList(userId, 3);
                log.info("-----userId={}：从数据库查询PassedDTO未通过记录-----", userId);
                if (!ListUtils.isEmpty(userRecordDTOS)) {
                    listOperations.leftPushAll(key, userRecordDTOS);
                    if(userRecordDTOS.size()>4){
                        list = userRecordDTOS.subList(0,3);
                        return list;
                    }
                }else {
                    UserRecordDTO userRecordDTO = new UserRecordDTO();
                    userRecordDTO.setName("暂无数据……");
                    userRecordDTO.setMemo(" ");
                    userRecordDTO.setUnit(" ");
                    listOperations.leftPush(key, userRecordDTO);
                    redisTemplate.expire(key, 10L, TimeUnit.MINUTES);
                }
                return userRecordDTOS;
            }

        }
        return null;
    }

    //TODO: 查询未归还方法1_
    public List<UserRecordDTO> selectNoReturnList(Integer userId){
        ListOperations<String, UserRecordDTO> listOperations = redisTemplate.opsForList();
        if (userId!=null) {
            String key = Constant.SelectNoReturn + userId;
            if (redisSrvice.existList(key)) {
                log.info("-----userId={}：从List缓存中查询PassedDTO未归还记录-----", userId);
                //查询五条记录
                list = listOperations.range(key, 0, 4);
                if (list != null) {
                    return list;
                }
            }else {
                //状态4是未归还
                List<UserRecordDTO> userRecordDTOS = viewMapper.selectUserRecordList(userId, 4);
                log.info("-----userId={}：从数据库查询PassedDTO未归还记录-----", userId);
                if (!ListUtils.isEmpty(userRecordDTOS)) {
                    listOperations.leftPushAll(key, userRecordDTOS);
                    if(userRecordDTOS.size()>4){
                        list = userRecordDTOS.subList(0,4);
                        return list;
                    }
                }else {
                    UserRecordDTO userRecordDTO = new UserRecordDTO();
                    userRecordDTO.setName("暂无数据……");
                    userRecordDTO.setMemo(" ");
                    userRecordDTO.setUnit(" ");
                    listOperations.leftPush(key, userRecordDTO);
                    redisTemplate.expire(key, 10L, TimeUnit.MINUTES);
                }
                return userRecordDTOS;
            }

        }
        return null;
    }

    //TODO: 查询申请中方法——系部
    public List<UserRecordDTO> selectUserInProByXBList(Integer userId){
        ListOperations<String, UserRecordDTO> listOperations = redisTemplate.opsForList();
        if (userId!=null) {
            String key = Constant.SelectInProByXB + userId;
            if (redisSrvice.existList(key)) {
                log.info("-----userId={}：从List缓存中查询PassedDTO系部审批记录-----", userId);
                //查询五条记录
                list = listOperations.range(key, 0, 1);
                if (list != null) {
                    return list;
                }
            }else {
                //状态5是系部审批
                List<UserRecordDTO> userRecordDTOS = viewMapper.selectUserInProByXBList(userId);
                log.info("-----userId={}：从数据库查询PassedDTO系部审批记录-----", userId);
                if (!ListUtils.isEmpty(userRecordDTOS)) {
                    listOperations.leftPushAll(key, userRecordDTOS);
                    if(userRecordDTOS.size()>3){
                        list = userRecordDTOS.subList(0,2);
                        return list;
                    }
                }else {
                    UserRecordDTO userRecordDTO = new UserRecordDTO();
                    userRecordDTO.setName(" ");
                    userRecordDTO.setMemo(" ");
                    userRecordDTO.setUnit(" ");
                    listOperations.leftPush(key, userRecordDTO);
                    redisTemplate.expire(key, 10L, TimeUnit.MINUTES);
                }
                return userRecordDTOS;
            }

        }
        return null;
    }

    //TODO: 查询申请中方法——教务
    public List<UserRecordDTO> selectUserInProByJWList(Integer userId){
        ListOperations<String, UserRecordDTO> listOperations = redisTemplate.opsForList();
        if (userId!=null) {
            String key = Constant.SelectInProByJW + userId;
            if (redisSrvice.existList(key)) {
                log.info("-----userId={}：从List缓存中查询PassedDTO教务审批记录-----", userId);
                //查询五条记录
                list = listOperations.range(key, 0, 1);
                if (list != null) {
                    return list;
                }
            }else {
                //状态6是教务审批
                List<UserRecordDTO> userRecordDTOS = viewMapper.selectUserInProByJWList(userId);
                log.info("-----userId={}：从数据库查询PassedDTO教务审批记录-----", userId);
                if (!ListUtils.isEmpty(userRecordDTOS)) {
                    listOperations.leftPushAll(key, userRecordDTOS);
                    if(userRecordDTOS.size()>3){
                        list = userRecordDTOS.subList(0,2);
                        return list;
                    }
                }else {
                    UserRecordDTO userRecordDTO = new UserRecordDTO();
                    userRecordDTO.setName(" ");
                    userRecordDTO.setMemo(" ");
                    userRecordDTO.setUnit(" ");
                    listOperations.leftPush(key, userRecordDTO);
                    redisTemplate.expire(key, 10L, TimeUnit.MINUTES);
                }
                return userRecordDTOS;
            }

        }
        return null;
    }

    //TODO: 查询未不良记录
    public List<UserRecordDTO> selectBadRecordList(Integer userId){
        ListOperations<String, UserRecordDTO> listOperations = redisTemplate.opsForList();
        if (userId!=null) {
            String key = Constant.SelectBadRecord + userId;
            if (redisSrvice.existList(key)) {
                log.info("-----userId={}：从List缓存中查询PassedDTO不良记录-----", userId);
                //查询五条记录
                list = listOperations.range(key, 0, 2);
                if (list != null) {
                    return list;
                }
            }else {
                //状态7是不良记录
                List<UserRecordDTO> userRecordDTOS = viewMapper.selectUserRecordList(userId, 7);
                log.info("-----userId={}：从数据库查询PassedDTO不良记录-----", userId);
                if (!ListUtils.isEmpty(userRecordDTOS)) {
                    listOperations.leftPushAll(key, userRecordDTOS);
                    if(userRecordDTOS.size()>4){
                        list = userRecordDTOS.subList(0,3);
                        return list;
                    }
                }else {
                    UserRecordDTO userRecordDTO = new UserRecordDTO();
                    userRecordDTO.setName("暂无数据……");
                    userRecordDTO.setMemo(" ");
                    userRecordDTO.setUnit(" ");
                    listOperations.leftPush(key, userRecordDTO);
                    redisTemplate.expire(key, 10L, TimeUnit.MINUTES);
                }
                return userRecordDTOS;
            }

        }
        return null;
    }

    //TODO: 无法归还
    @Transactional(rollbackFor = Exception.class)
    public void cannotReturn(NoReturnDTO noReturnDTO){
        //给用户不归还记录-1,同时给不良记录+1
        System.out.println("更改RecordUser数据库记录！！！！！！！！！");
        recodeUserMapper.updateNoReturnByUserID(Integer.parseInt(noReturnDTO.getUserId()));
        System.out.println("更改RecordUser缓存记录！！！！！！！！！");
        if (redisSrvice.exist(noReturnDTO.getUserId())){
            String result = redisSrvice.stringGet(noReturnDTO.getUserId()).toString();
            try {
                //objectmapper帮我们反序列化
                RecodeUser recodeUser = objectMapper.readValue(result, RecodeUser.class);
                recodeUser.setBadRecords(recodeUser.getBadRecords()+1);
                recodeUser.setNotReturn(recodeUser.getNotReturn()-1);
                redisSrvice.stringSet(noReturnDTO.getUserId(), objectMapper.writeValueAsString(recodeUser));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //TODO:更改apply_cons数据库——有问题，通过userId和cid定位……
        applyConsMapper.updateStateAndMemoByUserId(noReturnDTO);
        System.out.println("aid========" + noReturnDTO.getAid());

       // UserRecordDTO userRecordDTO = viewMapper.selectUserRecordByAid(Integer.valueOf(noReturnDTO.getAid()));
       // System.out.println(userRecordDTO.toString());
        //更改不良记录缓存
       // redisTemplate.opsForList().leftPush(Constant.SelectBadRecord+userRecordDTO.getUserId(), userRecordDTO);
        System.out.println("改好了！！！！！！！！！");

    }
}















