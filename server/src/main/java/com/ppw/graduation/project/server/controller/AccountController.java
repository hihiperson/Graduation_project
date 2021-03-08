package com.ppw.graduation.project.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppw.graduation.project.model.entity.SysLog;
import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.server.enums.Dynamic;
import com.ppw.graduation.project.server.service.AccountService;
import com.ppw.graduation.project.server.service.RabbitService;
import com.ppw.graduation.project.server.utils.CheckMsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/23 12:20
 */

//@RestController  传值会，返回字符串不跳转;
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CheckMsgCode checkMsgCode;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitService rabbitService;

    SysLog sysLog = new SysLog();


    //注册账号界面
    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
        //return "register";
    }



    //注册按钮处理页
    @RequestMapping("/sub_register")
    public String sub_register( HttpServletRequest request, User user){
        //插入数据库
        int res = accountService.insertSelective(user);
        if (res>0) {
            //将用户信息存入session
            HttpSession session = request.getSession();
            user.setUpwd("");
            session.setAttribute("user", user);
            try {
                //TODO: 插入数据库日志
                sysLog.setIp(Dynamic.ip);
                sysLog.setTime(Dynamic.useTime);
                sysLog.setUsername(user.getUname());
                sysLog.setOperation("注册用户");
                sysLog.setMethod("sub_register");
                user.setUpwd("******");
                sysLog.setParams(objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                sysLog.setMemo(e.getMessage());
                e.printStackTrace();
            }
            rabbitService.mqSendLog(sysLog);

            return "redirect:/index/person_index";
        }else {
            return "redirect:/account/register";
        }
        //return modelAndView;
    }

    //判断用户是否存在
    @RequestMapping("/isExistsUname")
    @ResponseBody
    public String isExistsUname(String uname){
        if (uname!=null || uname != ""){
            int i = accountService.isExistsUname(uname);
            return i+"";
        }else {
            return "";
        }
    }

    //判断用户是否存在
    @RequestMapping("/isExistsPhone")
    @ResponseBody
    public String isExistsPhone(String phone){
        if (phone!=null || phone != ""){
            int i = accountService.isExistsPhone(phone);
            return i+"";
        }else {
            return "";
        }
    }

    //TODO: 验证码是否通过——序列化变成字符串
    @RequestMapping("/checkMsgCode")
    @ResponseBody
    public String checkMsgCode(String tel, String msgCode) {
        boolean isOk = checkMsgCode.validateCode(tel, msgCode);
        if (isOk==true)
            return "200";
        else return "-1";
    }


    //TODO:登录注册页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //点击登录
    @RequestMapping("sub_login")
    public String sub_login( HttpServletRequest request, Model model, String uname, String upwd){
        User user = accountService.selectByUname(uname);
        if (user != null) {
            System.out.println(user.getUpwd()+"-----"+user.getUname());
            System.out.println(upwd+"-----"+uname);
            if(uname.equals(user.getUname()) && upwd.equals(user.getUpwd())){
                //将用户信息存入session
                HttpSession session = request.getSession();
                user.setUpwd("");
                session.setAttribute("user", user);
                try {
                    //TODO: 插入数据库日志
                    sysLog.setIp(Dynamic.ip);
                    sysLog.setTime(Dynamic.useTime);
                    sysLog.setUsername(user.getUname());
                    sysLog.setOperation("登录");
                    sysLog.setMethod("sub_login");
                    user.setUpwd("******");
                    sysLog.setParams(objectMapper.writeValueAsString(user));
                } catch (JsonProcessingException e) {
                    sysLog.setMemo(e.getMessage());
                    e.printStackTrace();
                }
                rabbitService.mqSendLog(sysLog);
                return "person_index";
            }else {
                model.addAttribute("err", "账户或密码错误~");
                return "login";
            }
        }else {
            model.addAttribute("err", "用户不存在~");
            return "login";
        }
    }

    //TODO: 忘记密码界面
    @RequestMapping("forget_password")
    public String forget_password(){
        return "forget_password";
    }

    //忘记密码提交界面
    @RequestMapping("sub_forget_password")
    public String sub_forget_password(String tel){
        return "redirect:/account/change_password?tel="+tel;
    }

    //TODO: 更改密码界面
    @RequestMapping("change_password")
    public String change_password(Model model, String tel){
        //System.out.println("tel=="+tel);
        if (tel != null){
            //使用input（hidden）接收
            model.addAttribute("tel", tel);
            return "change_password";
        }else{
            return "forget_password";
        }
    }

    //提交新账号密码
    @RequestMapping("sub_change_password")
    public String sub_change_password(User user){
        System.out.println(user.getTel());
        int res = accountService.updatePwdByTel(user);
        if (res > 0) {
            return "login";
        }else {
            return "change_password";
        }
    }


}












