package com.zhaoyc.redisspringboot.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className MyBloomFilter
 * @description: 布隆过滤器
 * @date 2022/5/22 20:59
 */
@Data
@Slf4j
public class MyBloomFilter {

    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 2 >> 16;

    /**
     * 初始化哈希函数
     */
    private final static List<Integer> seeds = Arrays.asList(
            3, 13, 46, 71, 91, 134
    );

    /**
     * 存放 hash值和哈希函数映射关系对象
     */
    private SimpleHash[] simpleHashes = new SimpleHash[seeds.size()];

    /**
     * 位数组
     */
    private BitSet bitSet = new BitSet(DEFAULT_SIZE);

    public MyBloomFilter() {
        for (int i = 0; i < seeds.size(); i++) {
            simpleHashes[i] = new SimpleHash(DEFAULT_SIZE, seeds.get(i));
        }
    }

    /**
     * 根据 哈希值 将位数组对应位置 置为 1
     * @param value
     */
    private void add(Object value) {
        Arrays.stream(simpleHashes)
                .forEach(simpleHash -> {
                    bitSet.set(simpleHash.hash(value), true);
                });
    }

    /**
     *
     * @param value
     * @return
     */
    private boolean contains(Object value) {
        boolean res = true;
        for (SimpleHash simpleHash: simpleHashes) {
            res = res && bitSet.get(simpleHash.hash(value));
        }
        return res;
    }

    /**
     * 静态内部类，用于存放对应哈希函数和哈希值的对象
     */
    @Data
    private static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }

    }

    public static void main(String[] args) {

        log.info("hash: {}", "zhaoyangchang".hashCode());
        MyBloomFilter filter = new MyBloomFilter();
        log.info("res: {}", filter.contains("zhaoyangchang"));
        log.info("res: {}", filter.contains("xielieping"));
        filter.add("zhaoyangchang");
        filter.add("xielieping");
        log.info("res: {}", filter.contains("zhaoyangchang"));
        log.info("res: {}", filter.contains("xielieping"));
    }

}
