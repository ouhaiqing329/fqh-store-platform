package com.fqh.storeserver.util;

public class SharedDataExample {
    static class SharedData {
        public int value = 0;
    }

//    public static void main(String[] args) {
//        SharedData sharedData = new SharedData();
//        sharedData.value = 43;
//
//        Thread childThread = new Thread(() -> {
//            sharedData.value = 42;
//            System.out.println("Child thread set value to: " + sharedData.value);
//        });
//
//        childThread.start();
//
////        try {
////            childThread.join();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//        System.out.println("Main thread sees value as: " + sharedData.value);
//    }


    // 创建ThreadLocal变量
    private static final ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0);

    private static final ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        // 主线程设置值
        threadLocalValue.set(100);
        threadLocal.set(99);
        System.out.println(Thread.currentThread().getName() + ": " + threadLocalValue.get());
        System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());

        // 创建新线程
        Thread thread = new Thread(() -> {

            System.out.println("master:" + Thread.currentThread().getName() + ": " + threadLocalValue.get());
            System.out.println("master:" + Thread.currentThread().getName() + ": " + threadLocal.get());
            // 子线程设置自己的值
            threadLocalValue.set(200);
            System.out.println(Thread.currentThread().getName() + ": " + threadLocalValue.get());

            // 使用后清理
            threadLocalValue.remove();
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 主线程再次获取
        System.out.println(Thread.currentThread().getName() + " after child thread: " + threadLocalValue.get());

        // 使用后清理
        threadLocalValue.remove();
    }
}