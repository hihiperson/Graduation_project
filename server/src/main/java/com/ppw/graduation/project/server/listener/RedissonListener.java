package com.ppw.graduation.project.server.listener;

import cn.hutool.core.util.StrUtil;
import com.ppw.graduation.project.model.mapper.MsgCodeMapper;
import com.ppw.graduation.project.server.enums.Constant;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/24 16:37
 */

//Redisson-监听机制
//让该监听在程序启动和运行期间可以一直执行
@Component
public class RedissonListener implements ApplicationRunner, Ordered {

    private static final Logger log = LoggerFactory.getLogger(RedissonListener.class);

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private MsgCodeMapper msgCodeMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("---应用在启动以及运行期间，执行自定义redisson监听---");
        listenExpireCode();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    //TODO：监听器-监听mqpCache里过期失效的验证码
    private void listenExpireCode(){
        RMapCache<String, String> rMapCache = redisson.getMapCache(Constant.RedissonMsgCodeKey);

        //调用元素失效时的监听器
        rMapCache.addListener(new EntryExpiredListener<String, String>() {
            @Override
            public void onExpired(EntryEvent<String, String> entryEvent) {
                String phone = entryEvent.getKey();
                String msgCode = entryEvent.getValue();
                log.info("--------当前手机号：{}，对应的验证码：{} -----即将失效", phone, msgCode);
                if (StrUtil.isNotBlank(phone) && StrUtil.isNotBlank(msgCode)){
                    //将数据库有效设置为0
                    int res = msgCodeMapper.updateExpirePhoneCode(phone, msgCode);
                    if (res > 0){
                        log.info("---验证码失效成功！---");
                    }
                }
            }
        });
    }
}
