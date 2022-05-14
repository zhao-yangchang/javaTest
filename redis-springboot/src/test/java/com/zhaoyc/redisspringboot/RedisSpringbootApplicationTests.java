package com.zhaoyc.redisspringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaoyc.redisspringboot.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class RedisSpringbootApplicationTests {

    @Autowired
    // 多个重名bean，指定目标bean
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        // redisTemplete.opsForXXX 操作不同的数据类型，api和redis指令一样
        // opsForValue 操作字符串 类似String
        redisTemplate.opsForValue().get("key");
        // opsForList 操作list 类似list
        redisTemplate.opsForList().range("key", 0, -1);
        // opsForSet
        redisTemplate.opsForSet();
        // opsForZSet
        redisTemplate.opsForZSet();
        // opsForHash
        redisTemplate.opsForHash();
        // opsForGeo
        redisTemplate.opsForGeo();

        // 除了基本操作，常用方法都可以直接通过redisTemplete操作；比如事务、基本的crud

        // 获取redis的连接
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        // 清库
        connection.flushDb();
    }

    /**
     * redis 默认序列化问题
     * @throws Exception
     */
    @Test
    public void seriTest() throws Exception {

        User user = new User("zyc", 27);
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", jsonUser);
        log.info("user: {}", redisTemplate.opsForValue().get("user"));
        // json正常输出 { "name": "zyc", "age": 27}
        // 直接传递没有序列化的对象会报错
    }

}
