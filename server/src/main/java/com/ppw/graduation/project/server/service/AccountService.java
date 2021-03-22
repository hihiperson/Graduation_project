package com.ppw.graduation.project.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.model.entity.SysLog;
import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.model.mapper.RecodeUserMapper;
import com.ppw.graduation.project.model.mapper.UserMapper;
import com.ppw.graduation.project.server.enums.Dynamic;
import com.ppw.graduation.project.server.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/24 11:22
 */

//有关账号得到业务逻辑
@Service
public class AccountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecodeUserMapper recodeUserMapper;


    /*判断用户是否存在：1存在，0不在*/
    public int isExistsUname(String uname){
        return userMapper.isExistsUname(uname);
    }

    public int isExistsPhone(String phone) {
        return userMapper.isExistsPhone(phone);
    }

    //将用户插入数据库
    //关联两个表，一个user，一个record_user
    //添加声明式事务
    @Transactional(rollbackFor = Exception.class)
    public User insertSelective(User user){
        userMapper.insertSelective(user);
        int id = user.getUserId();
        User user_temp = userMapper.selectByPrimaryKey(id);
        //为用户创建记录表
        recodeUserMapper.insertByUserId(user_temp.getUserId());
        return user_temp;

    }




    //查询用户
    public User selectByUname(String uname){
        return userMapper.selectByUname(uname);
    }

    //更改账户密码
    public int updatePwdByTel(User user){
        return userMapper.updateByTelSelective(user);
    }
}















