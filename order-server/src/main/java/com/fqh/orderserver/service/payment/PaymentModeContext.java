package com.fqh.orderserver.service.payment;

import com.fqh.orderserver.service.impl.payment.WXPaymentStrategy;
import com.fqh.orderserver.service.impl.payment.ZFBPaymentStrategy;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 付款上下文
 *
 * @author ouhaiqing
 * @date 2025/03/09
 */
//@Component
public class PaymentModeContext {


    private AbstractPaymentModeStrategy abstractPaymentModeStrategy;


//    public PaymentModeContext(@Qualifier("WXPaymentStrategy") PaymentModeStrategy paymentModeStrategy) {
//        this.paymentModeStrategy = paymentModeStrategy;
//    }

    public PaymentModeContext(AbstractPaymentModeStrategy abstractPaymentModeStrategy) {
        this.abstractPaymentModeStrategy = abstractPaymentModeStrategy;
    }




    void test() {
        System.out.println("策略模式测试");
        abstractPaymentModeStrategy.commonPay();
    }

    public static void main(String[] args) {
//        PaymentModeContext paymentModeContext = new PaymentModeContext(new WXPaymentStrategy());
//        paymentModeContext.test();

        PaymentModeContext paymentModeContext = new PaymentModeContext(new WXPaymentStrategy());
        paymentModeContext.test();
        
        
    }


}
