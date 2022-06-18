package com.zhaoyc.rabbitmqspringboot.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className ConnectionUtil
 * @description: rabbitmq 连接工具
 * @date 2022/6/17 22:22
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {

        // 1. 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 2. 在工厂对象中设置MQ的连接信息(ip、port、vhost、username、password)
        factory.setHost("xxx");
        factory.setPort(5672);
        factory.setVirtualHost("host1");
        factory.setUsername("zhaoyangchang");
        factory.setPassword("admin123");

        // 3. 通过工厂对象 获得 连接对象
        return factory.newConnection();
    }

}
