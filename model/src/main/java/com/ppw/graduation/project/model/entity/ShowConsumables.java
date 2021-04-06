package com.ppw.graduation.project.model.entity;

import com.ppw.graduation.project.model.dto.UserDTO;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private String department;
    private BigDecimal lend;
    private BigDecimal num;
    private BigDecimal starting_num;
    private int frequency;
    private Byte isActive;
    private List<UserDTO> userDTOList;
    //没有加keyword


    @Override
    public String toString() {
        return "ShowConsumables{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", lend=" + lend +
                ", num=" + num +
                ", starting_num=" + starting_num +
                ", frequency=" + frequency +
                ", isActive=" + isActive +
                ", userDTOList=" + userDTOList +
                '}';
    }

    public ShowConsumables() {
    }

    public ShowConsumables(int cid, String name, String department, BigDecimal lend, BigDecimal num, BigDecimal starting_num, int frequency, Byte isActive, List<UserDTO> userDTOList) {
        this.cid = cid;
        this.name = name;
        this.department = department;
        this.lend = lend;
        this.num = num;
        this.starting_num = starting_num;
        this.frequency = frequency;
        this.isActive = isActive;
        this.userDTOList = userDTOList;
    }

    public BigDecimal getLend() {
        return lend;
    }

    public void setLend(BigDecimal lend) {
        this.lend = lend;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getStarting_num() {
        return starting_num;
    }

    public void setStarting_num(BigDecimal starting_num) {
        this.starting_num = starting_num;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
