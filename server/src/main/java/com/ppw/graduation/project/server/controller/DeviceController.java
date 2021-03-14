package com.ppw.graduation.project.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/11 20:05
 */

//商品详情页controller

@RestController
@RequestMapping("device")
public class DeviceController {

    private static final Logger log  = LoggerFactory.getLogger(DeviceController.class);

    //TODO: 展示商品详情
    @RequestMapping("/detailByName")
    public ModelAndView detailByName(@RequestParam("name")String name, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("user")!=null){
            System.out.println(session.getAttribute("user"));
        }
        if (name!=""||!name.equals("")||name!=null){
            System.out.println("name========"+name);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("details.html");
        //查询所有耗材的选项
        return modelAndView;
    }
    @RequestMapping("/detailById")
    public ModelAndView detailById(@RequestParam("cid")int cid, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("user")!=null){
            System.out.println(session.getAttribute("user"));
        }
        System.out.println("cid========"+cid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("details.html");
        //查询所有耗材的选项
        return modelAndView;
    }

}
