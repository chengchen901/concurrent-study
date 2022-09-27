package com.study.concurrent;

import java.util.UUID;

/**
 * @author Hash
 * @since 2021/4/21
 */
public class Temp {
    public static Object iceCream = null;

    public static void main(String[] args) throws Exception {
        final Temp temp = new Temp();
        temp.test2_DeadLock();
    }

    public void test2_DeadLock() throws Exception {
        //开启一个线程，代表小朋友
        new Thread(() -> {
            if (iceCream == null) {
                //若没有冰激凌
                try {
                    Thread.sleep(5000L);
                    System.out.println("没有冰激凌，小朋友不开心，等待...");
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            System.out.println("小朋友买到冰激凌，开心回家");
        }).start();
        Thread.sleep(3000L);
        // 3秒之后
        iceCream = new Object();
        // 店员做了一个冰激凌
        synchronized (this) {
            // 通知小朋友
            this.notifyAll();
        }
        System.out.println("通知小朋友");
    }
}
