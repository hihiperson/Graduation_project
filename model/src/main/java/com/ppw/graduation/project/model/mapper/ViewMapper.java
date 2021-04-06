package com.ppw.graduation.project.model.mapper;

import com.ppw.graduation.project.model.dto.UserRecordDTO;
import com.ppw.graduation.project.model.entity.ShowConsumables;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/25 23:43
 */
public interface ViewMapper {

    //TODO:耗材展示
    List<ShowConsumables> selectShowConsumablesList();

    //TODO: 用户已通过展示
    List<UserRecordDTO> selectUserRecordList(@Param("userId") Integer userId, @Param("applyStateId") Integer applyStateId);

    List<UserRecordDTO> selectUserInProByXBList(Integer userId);
    List<UserRecordDTO> selectUserInProByJWList(Integer userId);

    UserRecordDTO selectUserRecordByAid(Integer aid);
}
