package com.zhaoyc.rabbitmqspringboot.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhaoyc.rabbitmqspringboot.utils.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className sendMsg
 * @description: 发送消息——消息生产者(简单模式)
 * @date 2022/6/17 22:41
 */
@Component
@Slf4j
public class sendMsg {

    public void sendMsg() {
        String msg = "Hello World";
        // 相当于JDBC操作的数据库连接
        try (Connection connection = ConnectionUtil.getConnection()) {
            // 相当于JDBC操作的statement
            Channel channel = connection.createChannel();

            // 定义队列（在MQ中新建一个队列）
            // 参数1：队列的名称
            // 参数2：是否持久化
            // 参数3：是否排他（是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问）
            // 参数4：自动删除（当此队列的连接数为0时，此队列会销毁，无论队列中是否还有数据）
            // 参数5：设置当前队列参数（）
            channel.queueDeclare("queue_test", false,false, false, null);

            // 参数1：交换机名称，如果直接发送消息到队列，则交换机名称为""
            // 参数2：目标队列名称
            // 参数3：设置当前消息的属性（设置过期时间：10）
            // 参数4：消息内容
            channel.basicPublish("","queue_test", null, msg.getBytes(StandardCharsets.UTF_8));

            channel.close();
            connection.close();
        } catch (Exception e) {
            log.warn("rabbitmq connect error: {}", e);
        }
    }

}
