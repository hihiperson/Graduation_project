package com.ppw.graduation.project.server.enums;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/26 0:20
 */

//通过拦截器获取ip等信息

public class Dynamic {

    public static String ip;

    public static Long useTime;

    public static void getIp(String ip_temp){
        ip = ip_temp;
        //System.out.println("ip=----------"+ip);
    }

    public static void getUseTime(Long time_temp){
        useTime = time_temp;
       // System.out.println("useTime=----------"+useTime);
    }
}
