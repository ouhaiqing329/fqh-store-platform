package com.fqh.storeserver;

import com.fqh.storeserver.util.SpringContextUtil;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.io.File;

/**
 * @author ouhaiqing
 * @date 2022/8/12 18:28
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
@MapperScan(basePackages = "com.fqh.**.mapper")
@Slf4j
@MapperScan(basePackages = "com.fqh.**.mapper")
public class StoreServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreServerApplication.class,args);
        log.info("==================StoreServer服务启动成功！======================");

        buy(500, 1024);
    }


    /**
     *      购买商品
     *
     * @param goodsPrice 商品价格
     * @param payNum     支付金额
     * @return int
     */
    public static void buy(int goodsPrice,int payNum){
        //货币面值
        int [] coinArray = {1024,64,16,4,1};
        int remainingCoinNum = payNum;
        for (int i = 0; i < coinArray.length; i++) {
            //如果支付金额大于面值
            int temp = remainingCoinNum / goodsPrice;
            if (temp < coinArray[i]){
                continue;
            }
            //剩余金额
            remainingCoinNum = temp % coinArray[i];
            //找回个数
            System.out.println("找回面额为:"+((temp - remainingCoinNum) / coinArray[i])+"个硬币");
        }
    }


    /**
     * 递归读指定文件夹下所有文件
     *
     * @param filePath 文件路径
     */
    public static void recursiveRead(String filePath){

        File file = new File(filePath);
        //文件直接读取名称
        if (file.isFile()){
            System.out.println(file.getAbsolutePath() + "/" + file.getName());
        }
        //判断是否是文件夹
        if (file.isDirectory()){
            //遍历所有文件
            File[] files = file.listFiles();
            //函数出口
            if (files == null){
                return;
            }
            for (File fe : files) {
                recursiveRead(fe.getAbsolutePath());
            }
        }
    }
}
