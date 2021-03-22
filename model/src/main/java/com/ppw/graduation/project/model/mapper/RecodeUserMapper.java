package com.ppw.graduation.project.model.mapper;

import com.ppw.graduation.project.model.entity.RecodeUser;

public interface RecodeUserMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(RecodeUser record);

    int insertSelective(RecodeUser record);

    RecodeUser selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(RecodeUser record);

    int updateByPrimaryKey(RecodeUser record);

    RecodeUser selectByUserId(Integer uid);

    int insertByUserId(int userId);
}