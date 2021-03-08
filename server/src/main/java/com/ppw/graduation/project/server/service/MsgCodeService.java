package com.ppw.graduation.project.server.service;

import com.ppw.graduation.project.model.entity.MsgCode;
import com.ppw.graduation.project.model.mapper.MsgCodeMapper;
import com.ppw.graduation.project.server.enums.Constant;
import com.ppw.graduation.project.server.utils.Msg_HttpClientUtil;
import com.ppw.graduation.project.server.utils.RandomUtil;
import jodd.util.StringUtil;
import org.joda.time.DateTime;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** 
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/24 15:50
 */

@Service
public class MsgCodeService {

    private final static Logger log = LoggerFactory.getLogger(MsgCodeService.class);

    @Autowired
    private MsgCodeMapper msgCodeMapper;

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private Environment env;

    /*获取短信验证码：redisson方式*/
    public String getRandomCode(final String phone){
        //TODO：在网络不稳定的情况下，可能收集没能即受到短信验证码，此时需要重新申请，即
        //TODO: 同个手机号多次申请验证码，如果该手机号存在着 15 min内有效的验证码，则直接取出发给他即可，而无需重新生成
        RMapCache<String, String> rMapCache = redisson.getMapCache(Constant.RedissonMsgCodeKey);
        String code = rMapCache.get(phone);
        if (StringUtil.isNotBlank(code)){
            return code;
        }

        //生成四位数的短信验证码
        String msgCode = RandomUtil.randomMsgCode(4);

        //TODO: 调用短信提供商提供的发送短信的api——要钱的，顶不住
//        String smsText = "用户注册验证码："+ msgCode + "，15分钟内有效";
//        send_msgCode(env.getProperty("sms.uid"), env.getProperty("sms.key"), phone, smsText);
        //TODO: 短信发送成功后在插入数据库

        //插入数据库
        MsgCode entity = new MsgCode(phone, msgCode);
        entity.setSendTime(DateTime.now().toDate());
        int res = msgCodeMapper.insertSelective(entity);
        if (res > 0){
            //key-value-失效期
            rMapCache.put(phone, msgCode, 15L, TimeUnit.MINUTES);
        }

        return msgCode;
    }


    //TODO：SMS短信的发送api调用
    /*
    //用户名
	private static String Uid = "Uid";
	//接口安全秘钥
	private static String Key = "Key";
	//手机号码，多个号码如13800000000,13800000001,13800000002
	private static String smsMob = "13800000000";
	//短信内容
	private static String smsText = "验证码：8888";
     */
    public void send_msgCode(String Uid, String Key, String smsMob, String smsText){
        Msg_HttpClientUtil client = Msg_HttpClientUtil.getInstance();
        //GBK发送
        //工具类中还有一个utf-8的发送
        int resultGbk = client.sendMsgGbk(Uid, Key, smsText, smsMob );
        if(resultGbk>0){
            System.out.println("GBK成功发送条数=="+resultGbk);
        }else{
            System.out.println(client.getErrorMsg(resultGbk));
        }
    }
}
