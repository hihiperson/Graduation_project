package com.ppw.graduation.project.server.controller;

import com.ppw.graduation.project.model.entity.User;
import com.ppw.graduation.project.server.service.FileService;
import com.ppw.graduation.project.server.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/1 1:02
 */

//主页
@Controller
@RequestMapping("index")

public class IndexController {

    @Autowired
    private Environment env;

    @Autowired
    private FileService fileService;

    @RequestMapping("person_index")
    public String person_index(HttpServletRequest request){
        HttpSession session = request.getSession();
        //如果已登录，进入index
        if (session.getAttribute("user")!=null){
            return "person_index";
        }else {
            //如果没有登录
            return "redirect:/account/login";
        }
    }

    //TODO：上传头像
    @PostMapping("fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null){
            fileService.photoUpload(file, user.getUname());
        }
        return "person_index";
    }
}
