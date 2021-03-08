package com.ppw.graduation.project.model.mapper;


import com.ppw.graduation.project.model.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int isExistsUname(String uname);

    int isExistsPhone(String phone);

    User selectByUname(String uname);

    int updateByTelSelective(User record);
}