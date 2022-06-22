package com.zhaoyc.rabbitmqspringboot.test;

import com.rabbitmq.client.*;
import com.zhaoyc.rabbitmqspringboot.utils.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className sendMsg
 * @description: 发送消息——消息生产者(简单模式) test
 * @date 2022/6/17 22:41
 */
@Component
@Slf4j
public class sendMsg {

    /**
     * 声明 交换机 和 队列
     */
    public void declare() {
        try {
            Connection connection = ConnectionUtil.getConnection();
            // 相当于JDBC操作的statement
            Channel channel = connection.createChannel();

            // 定义订阅交换机
            channel.exchangeDeclare("exchange_fanout", BuiltinExchangeType.FANOUT);
            // 定义路由交换机
            channel.exchangeDeclare("exchange_derect", BuiltinExchangeType.DIRECT);

            // 定义队列（在MQ中新建一个队列）
            // 参数1：队列的名称
            // 参数2：是否持久化
            // 参数3：是否排他（是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问）
            // 参数4：自动删除（当此队列的连接数为0时，此队列会销毁，无论队列中是否还有数据）
            // 参数5：设置当前队列参数（）
            channel.queueDeclare("queue_test", false,false, false, null);

            // 绑定队列
            // 参数1：队列名称
            // 参数2：目标交换机
            // 参数3：如果绑定订阅交换机，参数为""；如果绑定路由交换机则表示路由key
            channel.queueBind("queue_test", "exchange_fanout", "");
            channel.queueBind("queue_test", "exchange_direct", "a");

        } catch (Exception e) {
            log.warn("rabbitmq connect error: {}", e);
        }
    }

    public void sendMsg() {
        String msg = "Hello World";
        // 相当于JDBC操作的数据库连接
        try (Connection connection = ConnectionUtil.getConnection()) {
            // 相当于JDBC操作的statement
            Channel channel = connection.createChannel();

            // 参数1：交换机名称，如果直接发送消息到队列，则交换机名称为""
            // 参数2：目标队列名称
            // 参数3：设置当前消息的属性（设置过期时间：10）
            // 参数4：消息内容

            // exchange 和 routingKey同时指定——简单模式或工作模式
            // 不指定 routingKey ，该 excahnge（交换机）下绑定的queue都能接收到消息， 则为 发布订阅模式

            // 如果exchange不为空，则第二个参数是路由key
            // 如果exchange是空的，则第二个参数是队列
            channel.basicPublish("","queue_test", null, msg.getBytes(StandardCharsets.UTF_8));

            channel.close();
            connection.close();
        } catch (Exception e) {
            log.warn("rabbitmq connect error: {}", e);
        }
    }

    /**
     * rabbit mq 事务
     */
    public void mqTxSelect() {

        String msg = "Hello world";
        try {
            Connection connection = ConnectionUtil.getConnection();
            // 相当于JDBC操作的statement
            Channel channel = connection.createChannel();

            // 发送消息之前，开启消息确认
            channel.confirmSelect();
            // send msg
            channel.basicPublish("","queue_test", null, msg.getBytes(StandardCharsets.UTF_8));

            boolean res = channel.waitForConfirms();
            log.info("消息是否发送成功：{}", res);

            channel.close();
            connection.close();
        } catch (Exception e) {
            log.warn("rabbitmq connect error: {}", e);
        }

    }

    /**
     * mq 同步 消息确认
     */
    public void syncMqConfirmSelect() {
        String msg = "Hello world";
        try {
            Connection connection = ConnectionUtil.getConnection();
            // 相当于JDBC操作的statement
            Channel channel = connection.createChannel();

            // 发送消息之前，开启消息确认
            channel.confirmSelect();
            // send msg
            channel.basicPublish("","queue_test", null, msg.getBytes(StandardCharsets.UTF_8));
            boolean res1 = channel.waitForConfirms();
            log.info("消息是否发送成功：{}", res1);

            // batch send msg
            channel.confirmSelect();
            for (int i=0; i<10; i++) {
                // 批量发送消息，如果有一条失败，则全部失败
                channel.basicPublish("","queue_test", null, msg.getBytes(StandardCharsets.UTF_8));
            }
            // 消息发送后会等待确认（阻塞）
            boolean res2 = channel.waitForConfirms();
            log.info("消息是否发送成功：{}", res2);

            channel.close();
            connection.close();
        } catch (Exception e) {
            log.warn("rabbitmq connect error: {}", e);
        }
    }

    /**
     * 异步 消息确认
     * 不能 close
     */
    public void asyncListener() {
        String msg = "Hello world";
        try {
            Connection connection = ConnectionUtil.getConnection();
            // 相当于JDBC操作的statement
            Channel channel = connection.createChannel();

            // 发送消息之前，开启消息确认
            channel.confirmSelect();
            // 异步消息确认 监听器 —— 另一个线程，可以放在发消息之前
            channel.addConfirmListener(new ConfirmListener() {
                // success
                // 参数1：long 返回消息的标识
                // 参数2：Boolean 是否批量
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    log.info("success");
                }

                // fail
                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    log.info("fail");
                }
            });

            // 异步return监听：监听交换机是否将消息分发到队列
            channel.addReturnListener(new ReturnListener() {
                /**
                 * 如果交换机分发消息到队列失败，则会执行此方法（用来处理交换机分发消息到队列失败的情况）
                 * @param replyCode
                 * @param replyText
                 * @param exchange 交换机名称
                 * @param routingKey routing key
                 * @param properties
                 * @param body 消息
                 * @throws IOException
                 */
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {

                }
            });
            // lamda写法
            channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {

            });

            // send msg
            // channel.basicPublish("","queue_test", null, msg.getBytes(StandardCharsets.UTF_8));
            // 发送消息  添加参数3：开启return机制
            channel.basicPublish("","queue_test", true,null, msg.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.warn("rabbitmq connect error: {}", e);
        }
    }

}
