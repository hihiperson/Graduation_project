package com.ppw.graduation.project.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ApplyCons {
    private Integer aid;

    private Integer cid;

    private Integer userId;

    private BigDecimal applyNum;

    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat( pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date applyTime;

    private Integer applyDay;

    private String applyReson;

    private Byte isActive;

    private String keyword;

    private Byte applyStateId;

    private String memo;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(BigDecimal applyNum) {
        this.applyNum = applyNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        this.applyReson = applyReson == null ? null : applyReson.trim();
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Byte getApplyStateId() {
        return applyStateId;
    }

    public void setApplyStateId(Byte applyStateId) {
        this.applyStateId = applyStateId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}