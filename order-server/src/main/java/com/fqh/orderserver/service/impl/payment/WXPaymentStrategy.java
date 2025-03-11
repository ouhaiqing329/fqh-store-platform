package com.fqh.orderserver.service.impl.payment;


import com.fqh.orderserver.service.payment.AbstractPaymentModeStrategy;
import org.springframework.stereotype.Service;

/**
 * wx payment策略
 *
 * @author ouhaiqing
 * @date 2025/03/09
 */
@Service
public class WXPaymentStrategy extends AbstractPaymentModeStrategy {


    @Override
    public void test() {
        System.out.println("微信支付！");
    }


}
