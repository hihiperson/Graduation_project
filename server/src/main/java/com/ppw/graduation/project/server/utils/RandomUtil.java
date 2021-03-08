package com.ppw.graduation.project.server.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/24 15:25
 */

//随机数工具
public class RandomUtil {

    //短信验证码
    //参数：指定生成位数
    public static String randomMsgCode(final Integer num){
        return RandomStringUtils.randomNumeric(num);
    }

/*    //多线程压测
    private static final Integer total=1000;
    static class CodePlayer extends Thread{
        @Override
        public void run() {
            System.out.println(getName()+":"+randomMsgCode(4));
        }
    }

    public static void main(String[] args) {
        for (int i=1; i<total; i++){
            new CodePlayer().start();
        }
    }*/
}




















