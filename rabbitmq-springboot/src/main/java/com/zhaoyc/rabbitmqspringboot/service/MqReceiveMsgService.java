package com.zhaoyc.rabbitmqspringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @ClassName MqReceiveMsgService
 * @Description mq 消费者 service
 * @Author zhaoyangchang
 * @Date 2022/6/20 下午8:52
 * @Version 1.0.0
 */
@Service
// @RabbitListener(queues = {"queue1", "queue2"})
@RabbitListener(queues = "queue1") // mq 消息监听
@Slf4j
public class MqReceiveMsgService {

    /**
     * 接收数据 string
     * @param msg
     */
    @RabbitHandler
    public void receiveMsg(String msg) {
        log.info("[MqReceiveMsgService] [receiveMsg] msg: {}", msg);
    }

    /**
     * 接收数据 byte[]
     * @param msg
     */
    @RabbitHandler
    public void receiveMsg(byte[] msg) {

    }

}
