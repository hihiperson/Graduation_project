package com.ppw.graduation.project.model.mapper;

import com.ppw.graduation.project.model.entity.MsgCode;
import org.apache.ibatis.annotations.Param;

public interface MsgCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgCode record);

    int insertSelective(MsgCode record);

    MsgCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgCode record);

    int updateByPrimaryKey(MsgCode record);

    int updateExpirePhoneCode(@Param("phone") String phone, @Param("msgCode") String msgCode);
}