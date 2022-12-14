package com.study.concurrent.period6_1;


import java.util.concurrent.atomic.AtomicInteger;

public class KodyCountDownLatch_Test {

    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String args[]) throws InterruptedException {

        KodyCountDownLatch ctl = new KodyCountDownLatch(10);

        for (int i=0; i< 10; i++){
            new Thread(){
                @Override
                public void run() {
                    for (int j=0; j< 100000; j++){
                        num.getAndIncrement();
                    }
                    System.out.println("here1...");
                    ctl.countDown();
                }
            }.start();
        }

        ctl.await();     //设置开关，设置门栓
        System.out.println(num.get());
    }
}
