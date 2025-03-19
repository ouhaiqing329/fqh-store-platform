package com.fqh.storeserver.config.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;


@RocketMQMessageListener(consumerGroup = "", topic = "", consumeMode = ConsumeMode.CONCURRENTLY)
@Slf4j
public class RocketMQConsusumerListenerDemo implements RocketMQListener<Message>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("参数无效");
        }
        log.info("message:{}", message);

        byte[] body = message.getBody();

    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        /**
         * 费者客户端从先前停止的位置拾取。如果它是一个新启动的消费者客户端，根据消费者群体的年龄，有两种情况：
         *             如果该消费群体创建时间较晚，所订阅的最早的消息还没有过期，即该消费群体代表的是最近推出的业务，则消费将从头开始；
         *             如果订阅的最早消息已经过期，则消费将从最新消息开始，这意味着在引导时间戳之前生成的消息将被忽略。
         */
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    }
}
