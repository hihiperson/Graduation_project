package com.ppw.graduation.project.server.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ppw.graduation.project.model.dto.NoReturnDTO;
import com.ppw.graduation.project.model.dto.UserRecordDTO;
import com.ppw.graduation.project.model.entity.Consumables;
import com.ppw.graduation.project.model.entity.RecodeUser;
import com.ppw.graduation.project.model.entity.ShowConsumables;
import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.model.mapper.UserMapper;
import com.ppw.graduation.project.server.service.ConsService;
import com.ppw.graduation.project.server.service.FileService;
import com.ppw.graduation.project.server.service.Index2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/1 1:02
 */

//TODO: 普通用户主页
@RestController
@RequestMapping("index2")
public class Index2Controller {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(Index2Controller.class);

    @Autowired
    private Environment env;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Index2Service index2Service;

    @Autowired
    private FileService fileService;

    @Autowired
    private ConsService consService;

    @RequestMapping("person_index")
    public ModelAndView person_index(HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        //如果已登录，进入index
        if (session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            if (user.getLevel()==4 || user.getLevel()==1){
                //TODO: 查缓存数据……缓存没有就查数据库……
                RecodeUser recodeUser = index2Service.selectUserRecord(user.getUserId());
                List<UserRecordDTO> passedList = index2Service.selectPassedList(user.getUserId());
                List<UserRecordDTO> noPassedList = index2Service.selectNoPassedList(user.getUserId());
                List<UserRecordDTO> noReturnList = index2Service.selectNoReturnList(user.getUserId());
                List<UserRecordDTO> inProByXBList = index2Service.selectUserInProByXBList(user.getUserId());
                List<UserRecordDTO> inProByJWList = index2Service.selectUserInProByJWList(user.getUserId());
                List<UserRecordDTO> badRecordList = index2Service.selectBadRecordList(user.getUserId());
                modelAndView.setViewName("person_index");
                modelAndView.addObject("recodeUser", recodeUser);
                modelAndView.addObject("passedList", passedList);
                modelAndView.addObject("noPassedList", noPassedList);
                modelAndView.addObject("noReturnList", noReturnList);
                modelAndView.addObject("inProByXBList", inProByXBList);
                modelAndView.addObject("inProByJWList", inProByJWList);
                modelAndView.addObject("badRecordList", badRecordList);
            }
        }else {
            modelAndView.setViewName("redirect:/account/login");
        }

        return modelAndView;

    }

    //TODO: 无法归还
    @PostMapping("/cannotReturn")
    public ModelAndView cannotReturn(NoReturnDTO noReturnDTO){
        System.out.println(noReturnDTO.toString());
        //System.out.println("createTimecreateTimecreateTimecreateTime-------"+createTime);
        //index2Service.cannotReturn(cid, cname, userId, createTime, message);
        index2Service.cannotReturn(noReturnDTO);
        ModelAndView modelAndView = new ModelAndView("redirect:/index2/person_index");
        return modelAndView;
    }

    //TODO：上传头像
    @PostMapping("fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null){
            fileService.photoUpload(file, user.getUname());
            String picture = "../images/person/"+user.getUname()+".jpg";
            int res = userMapper.updatePicture(picture, user.getUserId());
            if (res > 0){
                user.setPicture(picture);
                session.setAttribute("user", user);
                return "redirect:/index2/person_index";
            }
        }
        return "error";
    }

    //TODO：显示所有商品
    @RequestMapping("AllCons")
    public ModelAndView AllCons(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null) {
            List<ShowConsumables> list = index2Service.selectShowConsumablesList();
//            for (ShowConsumables s : list) {
//                System.out.println(s.toString());
//                System.out.println(s.getUserDTOList().toString());
//            }
            modelAndView.addObject("showConsList", list);
            modelAndView.setViewName("show_cons");
            return modelAndView;
        }else {
            modelAndView.setViewName("redirect:/account/login");
        }
        return modelAndView;
    }

    //TODO：显示商品详情
    @RequestMapping("DetailCons")
    public ModelAndView DetailCons(HttpServletRequest request, @RequestParam("cid")int cid){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null) {
            Consumables consumables = consService.selectByPrimaryKey(cid);
            modelAndView.addObject("consumables", consumables);
            modelAndView.setViewName("details");
            return modelAndView;
        }else {
            modelAndView.setViewName("redirect:/account/login");
        }
        return modelAndView;
    }



}
