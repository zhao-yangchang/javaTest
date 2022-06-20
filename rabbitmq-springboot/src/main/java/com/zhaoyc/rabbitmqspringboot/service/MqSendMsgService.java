package com.zhaoyc.rabbitmqspringboot.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RabbitMqService
 * @Description rabbit mq service
 * @Author zhaoyangchang
 * @Date 2022/6/20 下午8:35
 * @Version 1.0.0
 */
@Component
public class MqSendMsgService {

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息 字符串、字节数组、序列化对象
     * @param msg
     */
    public void sendMsg(String msg) {
        // 1. 发送消息到队列
        amqpTemplate.convertAndSend("queue1", msg);

        // 2. 发送消息到交换机(订阅模式) —— 发送到指定交换机下到所有队列中
        amqpTemplate.convertAndSend("exchange1", "", msg);

        // 3. 发送消息到交换机（路由模式）—— 发送到交换机下的指定路由key的队列中
        amqpTemplate.convertAndSend("exchange2", "a", msg);
    }



}
