package com.fqh.orderserver.service.payment;

import com.fqh.orderserver.service.impl.payment.WXPaymentStrategy;
import com.fqh.orderserver.service.impl.payment.ZFBPaymentStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PaymentModeFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public PaymentModeStrategy getBean(int paymentMode) {

        switch (paymentMode) {
            case 1:
                return applicationContext.getBean(WXPaymentStrategy.class);
            case 2:
                return applicationContext.getBean(ZFBPaymentStrategy.class);

            default:
                throw new IllegalArgumentException("支付方式参数有误！");
        }

    }
}
