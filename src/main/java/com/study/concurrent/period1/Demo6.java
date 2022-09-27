package com.study.concurrent.period1;


public class Demo6 {
    static Demo6 ref = null;

    public static void main(String args[]) throws InterruptedException {
        //thread1
        new Thread(new Runnable() {
            @Override
            public void run() {
                Demo6 demo6 = new Demo6();

                ref = demo6;
            }
        }).start();

        //thread2
        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(ref.toString());

            }
        }).start();

        Thread.sleep(5000L);
    }
}
