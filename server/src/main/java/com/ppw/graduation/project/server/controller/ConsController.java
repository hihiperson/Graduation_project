package com.ppw.graduation.project.server.controller;


import com.ppw.graduation.project.model.entity.ApplyCons;
import com.ppw.graduation.project.server.service.ConsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/11 20:05
 */

//商品详情页controller

@RestController
@RequestMapping("cons")
public class ConsController {

    private static final Logger log  = LoggerFactory.getLogger(ConsController.class);

    @Autowired
    private ConsService consService;

    //TODO: 展示商品详情
    @RequestMapping("/sub_cons")
    public ModelAndView sub_cons(ApplyCons applyCons){
        ModelAndView modelAndView = new ModelAndView();
        if (applyCons!=null){
            int res = consService.applyCons(applyCons);
            if (res>0){
                modelAndView.setViewName("redirect:/index2/AllCons");
            }
        }else {
            modelAndView.setViewName("redirect:/index2/DetailCons?cid="+applyCons.getCid());;
        }
        return modelAndView;
    }
}
