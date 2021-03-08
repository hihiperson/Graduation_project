package com.ppw.graduation.project.server.utils;


import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ppw.graduation.project.server.enums.Constant.RedissonMsgCodeKey;


/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/28 21:28
 */

//TODO：校验验证码是否正确——有效期在15min内
@Component
public class CheckMsgCode {

    @Autowired
    private RedissonClient redisson;

    public boolean validateCode(final String phone, final String msgCode){
        //查询redis——失效了就不行咯~
        RMapCache<String, String> rMapCache = redisson.getMapCache(RedissonMsgCodeKey);
        String cacheCode = rMapCache.get(phone);
        return StringUtils.isNotBlank(cacheCode) && cacheCode.equals(msgCode);
    }
}
