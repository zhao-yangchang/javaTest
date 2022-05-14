package com.zhaoyc.redisspringboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className RedisUtils
 * @description: redis 工具类
 * @date 2022/5/14 21:40
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置过期时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time, TimeUnit unit) {
        if (StringUtils.isBlank(key) || Objects.isNull(time)) {
            return false;
        }

        if (Objects.isNull(unit)) {
            unit = TimeUnit.SECONDS;
        }

        try {
            redisTemplate.expire(key, time, unit);
            return true;
        } catch (Exception e) {
            log.error("[redis] [expire] error: {}", e);
            return false;
        }
    }

}
