package com.ppw.graduation.project.model.mapper;

import com.ppw.graduation.project.model.dto.NoReturnDTO;
import com.ppw.graduation.project.model.entity.ApplyCons;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ApplyConsMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(ApplyCons record);

    int insertSelective(ApplyCons record);

    ApplyCons selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(ApplyCons record);

    int updateByPrimaryKey(ApplyCons record);

    int updateStateAndMemoByUserId(NoReturnDTO noReturnDTO);
    //ApplyCons updateStateAndMemoByUserId(@Param("createTime") Date createTime, @Param("memo") String memo, @Param("userId")String userId, @Param("cid")String cid);
}