package com.ppw.graduation.project.model.mapper;


import com.ppw.graduation.project.model.entity.User;
import org.apache.ibatis.annotations.Param;

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

    int updatePicture(@Param("picture") String picture, @Param("userId")Integer userId);
}