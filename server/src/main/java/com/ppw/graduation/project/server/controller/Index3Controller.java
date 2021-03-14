package com.ppw.graduation.project.server.controller;

import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.model.mapper.UserMapper;
import com.ppw.graduation.project.server.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/1 1:02
 */

//TODO: 普通用户主页
@Controller
@RequestMapping("index3")
public class Index3Controller {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(Index3Controller.class);

    @Autowired
    private Environment env;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileService fileService;

    @RequestMapping("rear_index")
    public String rear_index(HttpServletRequest request){
        HttpSession session = request.getSession();
        //如果已登录，进入index
        if (session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            if (user.getLevel()==1 || user.getLevel()==3)  return "rear_index";
        }
        return "redirect:/account/login";

    }

}
