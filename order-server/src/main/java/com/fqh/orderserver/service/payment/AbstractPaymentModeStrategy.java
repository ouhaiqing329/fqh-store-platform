package com.fqh.orderserver.service.payment;

/**
 * 抽象支付模式模板
 *
 * @author ouhaiqing
 * @date 2025/03/11
 */
public abstract class AbstractPaymentModeStrategy implements PaymentModeStrategy{

    public final void commonPay() {
        log();
        test();
    }

    private void log() {
        System.out.println("打印日志");
    }


}
