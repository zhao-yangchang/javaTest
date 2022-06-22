package com.zhaoyc.rabbitmqspringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @ClassName MqSendMsgListenerService
 * @Description rabbitmq 开启监听 的 消息发送 service
 * @Author zhaoyangchang
 * @Date 2022/6/22 下午9:06
 * @Version 1.0.0
 */
@Component
@Slf4j
public class MqSendMsgListenerService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 为rabbitTemplate（amqp）声明当前类用于 消息确认 和 return 回调
     */
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 此方法用于监听消息确认结果（消息是否发送到交换机）
     * @param correlationData
     * @param b 是否发送成功
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

        if (b) {
            // 成功
            log.info("rabbitMq send msg success");
        } else {
            // 失败
            log.info("rabbitMq send msg failed");
        }

    }

    /**
     * 此方法用于return监听（当交换机分发消息到队列失败时执行）
     * @param returnedMessage
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.warn("rabbitMq exchange send msg to queue failed");
    }
}
