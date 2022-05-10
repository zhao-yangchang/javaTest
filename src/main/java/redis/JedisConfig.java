package redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

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

        // 常用api
        log.info("清空数据> ", jedis.flushDB());
        log.info("判断某个key是否存在>", jedis.exists("key"));
        log.info("新增<k1,v1>键值对>", jedis.set("k1", "v1"));
        Set<String> keys = jedis.keys("*");
        log.info("所有的key>", keys);

        log.info("删除键k1", jedis.del("k1"));
        log.info("查看键k1存储值的类型>", jedis.type("k1"));
        log.info("随机返回k1的一个元素>", jedis.randomKey());
        log.info("重命名k1 >", jedis.rename("k1", "k2"));
        log.info("按索引查询 >", jedis.select(0));
        log.info("返回当前数据库所有的key > ", jedis.dbSize());
        log.info("删除所有数据库的左右key > ", jedis.flushAll());

    }

}
