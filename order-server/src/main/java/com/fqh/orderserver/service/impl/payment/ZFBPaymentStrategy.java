package com.fqh.orderserver.service.impl.payment;

import com.fqh.orderserver.service.payment.PaymentModeStrategy;
import org.springframework.stereotype.Service;

@Service
public class ZFBPaymentStrategy implements PaymentModeStrategy {
    @Override
    public void test() {
        System.out.println("支付宝支付");
    }
}
