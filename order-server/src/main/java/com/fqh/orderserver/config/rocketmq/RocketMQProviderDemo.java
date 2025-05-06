package com.fqh.orderserver.config.rocketmq;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class RocketMQProviderDemo {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public void sendTransactionMessage(String msg) {
        String code = "001";
        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction("",
                MessageBuilder.withPayload(msg)
                        .setHeader(RocketMQHeaders.KEYS, code)
                        .build(), null);
        LocalTransactionState localTransactionState = transactionSendResult.getLocalTransactionState();

    }
}
