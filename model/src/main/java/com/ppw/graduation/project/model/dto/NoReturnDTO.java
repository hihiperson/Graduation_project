package com.ppw.graduation.project.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//无法归还封装临时类
public class NoReturnDTO {

    private String aid;
    private String cid;
    private String cname;
    private String userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    private String memo;

    @Override
    public String toString() {
        return "NoReturnDTO{" +
                "aid='" + aid + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", memo='" + memo + '\'' +
                '}';
    }

    public NoReturnDTO(){

    }


    public NoReturnDTO(String aid, String cid, String cname, String userId, Date createTime, String memo) {
        this.aid = aid;
        this.cid = cid;
        this.cname = cname;
        this.userId = userId;
        this.createTime = createTime;
        this.memo = memo;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
