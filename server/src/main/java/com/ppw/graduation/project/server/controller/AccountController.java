package com.ppw.graduation.project.server.controller;


import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.server.service.AccountService;
import com.ppw.graduation.project.server.service.CommonService;
import com.ppw.graduation.project.server.utils.CheckMsgCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(AccountController.class) ;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CheckMsgCode checkMsgCode;

    @Autowired
    private CommonService commonService;

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
        User res_user = accountService.insertSelective(user);
        if (res_user.getUserId()>0) {
            //将用户信息存入session
            HttpSession session = request.getSession();
            user.setUpwd("****");
            session.setAttribute("user", res_user);
            commonService.addLog(user.getUname(), "注册用户", "sub_register", res_user, "");
            log.info("------------用户注册成功-------------");
            return "redirect:/index2/person_index";
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
            if(uname.equals(user.getUname()) && upwd.equals(user.getUpwd())){
                //将用户信息存入session
                HttpSession session = request.getSession();
                user.setUpwd("****");
                session.setAttribute("user", user);
                commonService.addLog(user.getUname(), "用户登录", "sub_login", user, "");
                if ( user.getLevel()==4) {
                    return "redirect:/index2/person_index";
                }else if (user.getLevel()==1 || user.getLevel()==2){
                    return "redirect:/index/admin_index";
                }else if(user.getLevel()==3){
                    return "redirect:/index3/rear_index";
                }else {
                    return "error";
                }
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












