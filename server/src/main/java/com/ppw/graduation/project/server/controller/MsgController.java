package com.ppw.graduation.project.server.controller;

import com.ppw.graduation.project.api.response.BaseResponse;
import com.ppw.graduation.project.api.response.StatusCode;
import com.ppw.graduation.project.server.service.MsgCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/24 15:45
 */

//短信验证
@RestController
@RequestMapping("/msgCode")
public class MsgController {

    @Autowired
    private MsgCodeService msgCodeService;

    //获取短信验证码, 校验方法就省略了……
    @RequestMapping("/sendCode")
    public String sendCode(@RequestParam String phone){
        //TODO:前端已经判断了，要有手机号才能传递过来
        String msgCode = msgCodeService.getRandomCode(phone);
        return msgCode;
    }
}
