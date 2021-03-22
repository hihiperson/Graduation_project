package com.ppw.graduation.project.model.entity;

import java.io.Serializable;

public class RecodeUser implements Serializable {
    private Integer rid;

    private Integer userId;

    private Integer passed;

    private Integer inProgress;

    private Integer failed;

    private Integer notReturn;

    private Integer badRecords;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPassed() {
        return passed;
    }

    public void setPassed(Integer passed) {
        this.passed = passed;
    }

    public Integer getInProgress() {
        return inProgress;
    }

    public void setInProgress(Integer inProgress) {
        this.inProgress = inProgress;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    public Integer getNotReturn() {
        return notReturn;
    }

    public void setNotReturn(Integer notReturn) {
        this.notReturn = notReturn;
    }

    public Integer getBadRecords() {
        return badRecords;
    }

    public void setBadRecords(Integer badRecords) {
        this.badRecords = badRecords;
    }
}