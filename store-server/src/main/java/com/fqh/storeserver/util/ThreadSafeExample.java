package com.fqh.storeserver.util;

import java.util.concurrent.*;

public class ThreadSafeExample {

    public static void main(String[] args) {

        //concurrentMap线程数据共享
        ConcurrentMap<String, Integer> shareMap = new ConcurrentHashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService.submit(() -> {
                shareMap.put("TaskId:" + taskId, taskId * 10);
                System.out.println(Thread.currentThread().getName() + " :put data");
            });
        }
        //关闭线程池
        executorService.shutdown();


        //等待所有线程执行完成
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //打印最终数据
        System.out.println("final Map:"+shareMap);


    }
}
