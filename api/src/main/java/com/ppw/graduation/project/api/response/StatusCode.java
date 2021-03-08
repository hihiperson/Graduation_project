package com.ppw.graduation.project.api.response;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/21 21:34
 */

//统一响应接口，状态码
public enum StatusCode {

    Success(200, "成功"),
    Fail(-1, "失败"),
    TimeOut(3, "请求超时"),
    MQERROR(4, "rabbitmq存入日志记录失败"),
    NoUser(5, "用户不存在"),
    Photo_Fail(-6, "上传图片失败")
    ;

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
