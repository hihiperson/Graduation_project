import com.ppw.graduation.project.server.MainApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/27 15:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class TestRedis {

    private static final Logger log = LoggerFactory.getLogger(TestRedis.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testList() throws InterruptedException {
        log.info("------开始列表List测试------");
        final String key = "Graduation:Redis:List:1010";
        redisTemplate.delete(key);
        ListOperations listOperations = redisTemplate.opsForList();
        //单个插入
        listOperations.leftPush(key, "a");
        listOperations.leftPush(key, "b");
        //批量插入
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "d");
        listOperations.leftPushAll(key, list);

        //打印
        log.info("----当前列表元素个数：{}", listOperations.size(key));
        log.info("----当前列表元素：{}", listOperations.range(key, 0, 5));
        redisTemplate.expire(key, 10L, TimeUnit.SECONDS);
        Thread.sleep(10000);
        log.info("----当前列表元素个数：{}", listOperations.size(key));
        log.info("----当前列表元素：{}", listOperations.range(key, 0, 5));
        //listOperations.range(key, 0, 5);
//        log.info("------当前列表下标为0的元素:{}", listOperations.index(key, 0L));
//        log.info("------当前列表下标为4的元素:{}", listOperations.index(key, 4L));
//        log.info("------当前列表下标为10的元素:{}", listOperations.index(key, 10L));
//
//        log.info("-----当前列表从右边弹出来的元素：{}", listOperations.rightPop(key));
//
//        listOperations.set(key, 0L, "100");
//        log.info("------当前列表下标为0的元素:{}", listOperations.index(key, 0L));
//
//        //删除
//        listOperations.remove(key, 0L, "100");
//        log.info("------当前列表元素:{}", listOperations.range(key, 0, 10));
    }

    @Test
    public void testHash(){
        log.info("------开始Hash测试------");
        final String key = "Graduation:Redis:Hash:1010";
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, "10010", "zhangsan");
        hashOperations.put(key, "10011", "lisi");
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("10012", "wangwu");
        dataMap.put("10013", "zhaoliu");
        hashOperations.putAll(key, dataMap);
        log.info("---哈希hash：{}", hashOperations.entries(key));
        log.info("---哈希hash：获取10012的元素：{}", hashOperations.get(key, "10012"));
        log.info("---哈希hash：获取所有元素的field列表：{}", hashOperations.keys(key));
        log.info("---哈希hash：10013成员是否存在：{}", hashOperations.hasKey(key, "10013"));
        log.info("---哈希hash：10014成员是否存在：{}", hashOperations.hasKey(key, "10014"));

        hashOperations.putIfAbsent(key, "10020", "sunwukong");
        log.info("---哈希hash：获取列表元素：{}", hashOperations.entries(key));
        log.info("---哈希hash：删除元素10010 10011：{}", hashOperations.delete(key, "10010", "10011"));
        log.info("---哈希hash：获取列表元素：{}", hashOperations.entries(key));
        log.info("---哈希hash：获取列表长度：{}", hashOperations.size(key));
    }
}





















