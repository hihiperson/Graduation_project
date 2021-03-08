package com.ppw.graduation.project.server.service;

import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.model.mapper.UserMapper;
import com.ppw.graduation.project.server.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /*判断用户是否存在：1存在，0不在*/
    public int isExistsUname(String uname){
        return userMapper.isExistsUname(uname);
    }

    public int isExistsPhone(String phone) {
        return userMapper.isExistsPhone(phone);
    }

    //将用户插入数据库
    public int insertSelective(User user){
        return userMapper.insertSelective(user);
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















