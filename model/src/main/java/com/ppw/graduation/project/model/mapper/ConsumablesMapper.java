package com.ppw.graduation.project.model.mapper;

import com.ppw.graduation.project.model.entity.Consumables;

public interface ConsumablesMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Consumables record);

    int insertSelective(Consumables record);

    Consumables selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Consumables record);

    int updateByPrimaryKey(Consumables record);
}