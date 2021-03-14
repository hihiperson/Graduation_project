package com.ppw.graduation.project.server.controller;

import com.ppw.graduation.project.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/11 23:37
 */

//TODO: 管理员首页
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(Index2Controller.class);

    @RequestMapping("admin_index")
    public ModelAndView admin_index(HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView("admin_index");
        //如果已登录，进入index
        if (session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            if (user.getLevel()==1 || user.getLevel()==2)  return modelAndView;
        }else {
            modelAndView.setViewName("redirect:/account/login");
        }
        return modelAndView;
    }
}
