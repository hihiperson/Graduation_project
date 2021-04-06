package com.ppw.graduation.project.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/25 23:35
 */

//TODO：用户已通过的申请信息
public class UserRecordDTO implements Serializable {

    private Integer aid;
    private Integer userId;
    private Integer cid;

    private Integer approvalPerson1;    //系部审批
    private Integer approvalPerson2;    //教务审批


    private String name;

    private String unit;

    private BigDecimal applyNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat( pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date applyTime;

    private Integer applyDay;

    private String applyReson;

    private Byte applyStateId;

    private String memo;

    @Override
    public String toString() {
        return "UserRecordDTO{" +
                "aid=" + aid +
                ", userId=" + userId +
                ", cid=" + cid +
                ", approvalPerson1=" + approvalPerson1 +
                ", approvalPerson2=" + approvalPerson2 +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", applyNum=" + applyNum +
                ", applyTime=" + applyTime +
                ", applyDay=" + applyDay +
                ", applyReson='" + applyReson + '\'' +
                ", applyStateId=" + applyStateId +
                ", memo='" + memo + '\'' +
                '}';
    }

    public UserRecordDTO() {
    }

    public UserRecordDTO(Integer aid, Integer userId, Integer cid, Integer approvalPerson1, Integer approvalPerson2, String name, String unit, BigDecimal applyNum, Date applyTime, Integer applyDay, String applyReson, Byte applyStateId, String memo) {
        this.aid = aid;
        this.userId = userId;
        this.cid = cid;
        this.approvalPerson1 = approvalPerson1;
        this.approvalPerson2 = approvalPerson2;
        this.name = name;
        this.unit = unit;
        this.applyNum = applyNum;
        this.applyTime = applyTime;
        this.applyDay = applyDay;
        this.applyReson = applyReson;
        this.applyStateId = applyStateId;
        this.memo = memo;
    }

    public Integer getApprovalPerson1() {
        return approvalPerson1;
    }

    public void setApprovalPerson1(Integer approvalPerson1) {
        this.approvalPerson1 = approvalPerson1;
    }

    public Integer getApprovalPerson2() {
        return approvalPerson2;
    }

    public void setApprovalPerson2(Integer approvalPerson2) {
        this.approvalPerson2 = approvalPerson2;
    }

    public Byte getApplyStateId() {
        return applyStateId;
    }

    public void setApplyStateId(Byte applyStateId) {
        this.applyStateId = applyStateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(BigDecimal applyNum) {
        this.applyNum = applyNum;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Integer applyDay) {
        this.applyDay = applyDay;
    }

    public String getApplyReson() {
        return applyReson;
    }

    public void setApplyReson(String applyReson) {
        this.applyReson = applyReson;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
