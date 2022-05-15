package com.zhaoyc.redisspringboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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

    /**
     * 获取过期时间
     * @param key
     * @param unit
     * @return
     */
    public long getExpire(String key, TimeUnit unit) {
        if (StringUtils.isBlank(key)) {
            return 0;
        }
        if (Objects.isNull(unit)) {
            unit = TimeUnit.SECONDS;
        }

        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除一个或多个 key
     * @param keyList
     */
    public void del(String... keyList) {
        if (Objects.isNull(keyList) || keyList.length == 0) {
            return;
        }

        redisTemplate.delete(Arrays.asList(keyList));
    }

    //***************** hash 操作 start***********************//

    /**
     * 获取普通缓存
     * @param key
     * @return
     */
    public Object get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置普通缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.warn("[redisTemplete] [setValue] error: {}", e);
            return false;
        }
    }

    /**
     * 普通缓存并设置超时时间
     * @param key
     * @param value
     * @param expire
     * @param unit
     * @return
     */
    public boolean setex(String key, Object value, long expire, TimeUnit unit) {
        if (StringUtils.isBlank(key) || Objects.isNull(expire)) {
            return false;
        }

        if (Objects.isNull(unit)) {
            unit = TimeUnit.SECONDS;
        }

        try {
            redisTemplate.opsForValue().set(key, value, expire, unit);
            return true;
        } catch (Exception e) {
            log.warn("[redisTemplate] [setex] error: {}", e);
            return false;
        }
    }

    /**
     * 递增
     * @param key
     * @param delta 递增因子
     * @return
     */
    public long incr(String key, long delta) {
        if (StringUtils.isBlank(key) || delta < 0) {
            throw new RuntimeException("参数异常， key:  " + key + "delta: " + delta);
        }

        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key
     * @param delta
     * @return
     */
    public long decr(String key, long delta) {
        if (StringUtils.isBlank(key) || delta < 0) {
            throw new RuntimeException("参数异常， key:  " + key + "delta: " + delta);
        }

        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * hashGet
     * @param key
     * @param hashKey
     * @return
     */
    public Object hGet(String key, String hashKey) {
        if (StringUtils.isAnyBlank(key, hashKey)) {
            return null;
        }

        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取key对应的全部键值对
     * @param key
     * @return
     */
    public Map<Object, Object> hmGet(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * hashSet
     * @param key
     * @param map
     * @return
     */
    public boolean hmSet(String key, Map<String, Object> map) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e){
            log.warn("[redisTemplate] [hmSet] error: {}", e);
            return false;
        }
    }

    /**
     * 向某个hash集合插入键值<k, v>，如果不存在则创建
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean hSet(String key, String hashKey, Object value) {
        if (StringUtils.isAnyBlank(key, hashKey)) {
            return false;
        }

        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            log.warn("[redisTemplate] [hSet] error: {}", e);
            return false;
        }
    }

    /**
     * hashSet 并 设置过期时间
     * @param key
     * @param map
     * @param expire
     * @param unit
     * @return
     */
    public boolean hmSetex(String key, Map<String, Object> map, long expire, TimeUnit unit) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (expire > 0) {
                expire(key, expire, unit);
            }
            return true;
        } catch (Exception e) {
            log.warn("[redisTemplate] [hmSetex] error: {}", e);
            return false;
        }
    }

    /**
     * hDel
     * @param key
     * @param hashKeyList
     * @return
     */
    public boolean hDel(String key, String... hashKeyList) {
        if (StringUtils.isBlank(key)
                || Objects.isNull(hashKeyList)
                ||CollectionUtils.isEmpty(Arrays.asList(hashKeyList))) {
            return false;
        }
        try {
            redisTemplate.opsForHash().delete(key, hashKeyList);
            return true;
        } catch (Exception e) {
            log.warn("[redisTemplate] [hDel] error: {}", e);
            return false;
        }
    }

    /**
     * 判断某个hash集合是否有该键
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hExist(String key, String hashKey) {
        if (StringUtils.isAnyBlank(key, hashKey)) {
            return false;
        }
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 返回某个hash集合某个键对应值的自增后的值
     * @param key
     * @param hashKey
     * @param delta 自增因子
     * @return
     */
    public double hIncr(String key, String hashKey, double delta) {
        if (StringUtils.isAnyBlank(key, hashKey) || delta < 0) {
            throw new RuntimeException("参数异常， key:  " + key + "hashKey: " + hashKey + "delta: " + delta);
        }
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * 递减
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    public double hDecr(String key, String hashKey, double delta) {
        if (StringUtils.isAnyBlank(key, hashKey) || delta < 0) {
            throw new RuntimeException("参数异常， key:  " + key + "hashKey: " + hashKey + "delta: " + delta);
        }
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    //***************** hash 操作 start***********************//

    /**
     * 根据key获取set中所有的值
     * @param key
     * @return
     */
    public Set<Object> sGet(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 判断某个set集合是否包含某个value
     * @param key
     * @param value
     * @return
     */
    public boolean sExist(String key, Object value) {
        try {
            redisTemplate.opsForSet().isMember(key, value);
            return true;
        } catch (Exception e) {
            log.warn("[redisTemplate] [sExist] error: {}", e);
            return false;
        }
    }

    /**
     * 将数据放入set集合中
     * @param key
     * @param value
     * @return
     */
    public long sSet(String key, Object... value) {
        try {
            return redisTemplate.opsForSet().add(key, value);
        } catch (Exception e) {
            log.warn("[redisTemplate] [sSet] error: {}", e);
            return 0;
        }
    }

    /**
     * sSet 并 设置失效时间
     * @param key
     * @param expire
     * @param unit
     * @param value
     * @return
     */
    public boolean sSetex(String key, long expire, TimeUnit unit, Object... value) {
        try {
            redisTemplate.opsForSet().add(key, value);
            if (expire > 0) {
                expire(key, expire, unit);
            }
            return true;
        } catch (Exception e) {
            log.warn("[redisTemplate] [sSetex] error: {}", e);
            return false;
        }
    }

    /**
     * 获取set集合长度
     * @param key
     * @return
     */
    public long sLens(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.warn("[redisTemplate] [sLens] error: {}", e);
            return 0;
        }
    }

    

}
