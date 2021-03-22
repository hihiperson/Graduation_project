package com.ppw.graduation.project.model.entity;

import com.ppw.graduation.project.model.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/21 20:39
 */

//视图类
public class ShowConsumables implements Serializable {
    private int cid;
    private String name;
    private int num;
    private int frequency;
    private Byte isActive;
    private List<UserDTO> userDTOList;
    //没有加keyword


    public ShowConsumables() {
    }

    @Override
    public String toString() {
        return "ShowConsumables{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", frequency=" + frequency +
                ", isActive=" + isActive +
                ", userDTOList=" + userDTOList +
                '}';
    }

    public ShowConsumables(int cid, String name, int num, int frequency, Byte isActive, List<UserDTO> userDTOList) {
        this.cid = cid;
        this.name = name;
        this.num = num;
        this.frequency = frequency;
        this.isActive = isActive;
        this.userDTOList = userDTOList;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }
}
