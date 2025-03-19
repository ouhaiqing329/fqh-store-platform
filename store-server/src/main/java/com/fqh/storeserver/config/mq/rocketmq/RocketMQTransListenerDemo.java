package com.fqh.storeserver.config.mq.rocketmq;


import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

@RocketMQTransactionListener
public class RocketMQTransListenerDemo implements RocketMQLocalTransactionListener {


    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        //消息实体
        String payload = new String(((byte[]) message.getPayload()));
        //处理

        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     * 检查本地事务
     *
     * @param message 消息
     * @return {@link RocketMQLocalTransactionState }
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {

        String code = (String) message.getHeaders().get(RocketMQHeaders.KEYS);
        //使用code判断事务是否执行成功

        return code == null ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
    }
}
