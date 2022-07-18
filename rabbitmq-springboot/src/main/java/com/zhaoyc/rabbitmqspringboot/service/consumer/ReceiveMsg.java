package com.zhaoyc.rabbitmqspringboot.service.consumer;

import com.rabbitmq.client.*;
import com.zhaoyc.rabbitmqspringboot.utils.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className ReceiveMs
 * @description: 简单模式——消息接受者
 * @date 2022/6/18 20:35
 */
@Component
@Slf4j
public class ReceiveMsg {

    public void receiveMsg() {

        // 1. 获取连接
        try {
            Connection connection = ConnectionUtil.getConnection();
            Channel channel = connection.createChannel();

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // body 就是从队列中获取的数据
                    String msg = new String(body);
                    log.info("receive: {}", msg);
                }
            };

            // 参数1：队列名
            // 参数2：是否应答（消息确认）
            // 参数3：consumer
            channel.basicConsume("", true, consumer);

        } catch (Exception e) {
            log.warn("error: {}", e);
        }

    }

}
