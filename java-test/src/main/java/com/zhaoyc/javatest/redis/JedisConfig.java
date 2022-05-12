package com.zhaoyc.javatest.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className JedisConfig
 * @description: TODO
 * @date 2022/5/10 22:09
 */
@Slf4j
public class JedisConfig {

    public static void main(String[] args) {
        // new jedis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 输出 PONG 代表连接成功
        log.info("jedis connect res: {}", jedis.ping());

        // String常用api
        stringLog(jedis);

        // list常用api
        listLog(jedis);

        // set常用api
        setLog(jedis);

        // hash常用api
        hashLog(jedis);

        // 开启事务
        Transaction multi = jedis.multi();

        try {
            multi.set("user1", "value1");
            multi.set("user2", "value2");

            // 执行事务
            multi.exec();
        } catch (Exception e) {
            log.error("error: {}", e);
            // 放弃事务
            multi.discard();
        } finally {
            log.info("user1: {}", multi.get("user1"));
            log.info("user2: {}", multi.get("user2"));
            // 关闭连接
            jedis.close();
        }
    }

    /**
     * String常用api
     * @param jedis
     */
    private static void stringLog(Jedis jedis) {
        // String常用api
        log.info("清空数据> ", jedis.flushDB());
        log.info("判断某个key是否存在>", jedis.exists("key"));
        log.info("新增<k1,v1>键值对>", jedis.set("k1", "v1"));
        log.info("在key后面加入 >", jedis.append("k1", "append"));
        log.info("增加多个键值对 >", jedis.mset("k1", "v1", "k2", "v2"));
        log.info("获取多个键值对 >", jedis.mget("k1", "k2"));
        log.info("删除多个键值对 >", jedis.del("k1", "k2"));
        Set<String> keys = jedis.keys("*");
        log.info("所有的key>", keys);

        // 分布式锁常用
        log.info("没有则新增，存在则不set >", jedis.setnx("k1", "v1"));

        // 设置有效期
        log.info("新增键值对，并设置有效期 >", jedis.setex("k1", 10,"v1"));

        // 先获取原值，再更新为新值 同步输出k1原值，下次get输出新值
        log.info("获取原值，更新为新值 >", jedis.getSet("k1", "newValue"));

        // 获取k1的某个区间的值 [1, 2]
        log.info("k1某个区间的值 >", jedis.getrange("k1", 1, 2));
        // 获取k1全部区间的值 [0, -1]
        log.info("k1全部区间的值 >", jedis.getrange("k1", 0, -1));

        log.info("删除键k1", jedis.del("k1"));
        log.info("查看键k1存储值的类型>", jedis.type("k1"));
        log.info("随机返回k1的一个元素>", jedis.randomKey());
        log.info("重命名k1 >", jedis.rename("k1", "k2"));
        log.info("按索引查询 >", jedis.select(0));
        log.info("返回当前数据库所有的key > ", jedis.dbSize());
        log.info("删除所有数据库的左右key > ", jedis.flushAll());
    }

    /**
     * list常用api
     * @param jedis
     */
    private static void listLog(Jedis jedis) {
        // list常用api
        log.info("添加一个list >", jedis.lpush("list1", "array1", "array2", "array3", "array4"));
        log.info("向list1添加元素 >", jedis.lpush("list1", "array2"));
        // -1 代表倒数第一个元素，-2代表倒数第二个元素
        log.info("输出list元素 >", jedis.lrange("list1", 0 , -1));
        // count 代表删除的个数，后add的值先被删除，类似于出栈
        log.info("删除列表指定元素 >", jedis.lrem("list1", 2, "array2"));
        // 删除下标0-3区间之外的元素（等同于截取0-3区间之内的元素）
        log.info("截取0-3区间之内的元素 >", jedis.ltrim("list1", 0, 3));
        log.info("列表左端出栈1个元素 >", jedis.lpop("list1"));
        log.info("从列表右端添加元素（和lpush相反） >", jedis.rpush("list1", "value0"));
        log.info("列表右端出栈1个元素 >", jedis.rpop("list1"));
        log.info("修改k1列表下标1元素 >", jedis.lset("k1", 1, "v1New"));
        log.info("显示k1的长度 >", jedis.llen("k1"));
        log.info("获取k1列表下标为2的元素 >", jedis.lindex("k1", 2));
        log.info("对k1进行排序 >", jedis.sort("k1"));
    }

    /**
     * set常用api
     * @param jedis
     */
    private static void setLog(Jedis jedis) {
        // set常用api
        log.info("向集合中添加元素（不重复）>", jedis.sadd("s1", "v1", "v2", "v3"));
        log.info("查询集合所有元素 >", jedis.smembers("s1"));
        log.info("删除集合中的一个元素 >", jedis.srem("s1", "v2"));
        log.info("删除集合中的两个元素 >", jedis.srem("s1", "v2", "v3"));
        log.info("随机删除集合中的一个元素 >", jedis.spop("s1"));
        log.info("集合包含的元素个数 >", jedis.scard("s1"));
        log.info("判断元素是否在集合中 >", jedis.sismember("s1", "v3"));

        // 两个集合间的操作
        log.info("集合1 >", jedis.sadd("s1", "v1", "v2", "v3", "v4"));
        log.info("集合2 >", jedis.sadd("s2", "v1", "v3", "v4", "v5"));
        log.info("删除集合1元素，并存入集合3 >", jedis.smove("s1", "s3", "v3"));
        log.info("集合1和集合2的交集 >", jedis.sinter("s1", "s2"));
        log.info("集合1和集合2的并集 >", jedis.sunion("s1", "s2"));
        // s1有，s2没有
        log.info("集合1和集合2的差集 >", jedis.sdiff("s1", "s2"));

        // 求s1、s2交集并将交集保存到（s3）dstkey集合
        jedis.sinterstore("s3", "s1", "s2");
    }

    /**
     * hash常用api
     * @param jedis
     */
    private static void hashLog(Jedis jedis) {
        // hash常用api
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        // 添加hash集合
        jedis.hmset("h1", map);
        log.info("向h1集合添加<k4, v4>", jedis.hset("h1", "key4", "value4"));
        log.info("h1集合的所有键值对", jedis.hgetAll("h1"));
        log.info("h1集合的所有键", jedis.hkeys("h1"));
        log.info("h1集合的所有值", jedis.hvals("h1"));
        log.info("将key5保存的值加上一个整数，如果key5不存在则添加key5 >", jedis.hsetnx("h1", "key5", "value5"));
        log.info("删除一个或多个键值对 >", jedis.hdel("h1", "key1", "key2"));
        log.info("集合中键值对的个数 >", jedis.hlen("h1"));
        log.info("判断集合中是否存在key1 >", jedis.hexists("h1", "key1"));
        log.info("获取集合中的值 >", jedis.hmget("key1", "key2"));
    }
}
