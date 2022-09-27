package com.study.concurrent.period6;

public class DeadLock2 {


    static String lock = "";

    public static void main(String args[]) throws InterruptedException {


        synchronized (DeadLock2.class){
            System.out.println("notify...");
            DeadLock2.class.notify();

        }

        Thread th1 = new Thread(){
            @Override
            public void run() {
                synchronized (DeadLock2.class){
                    try {
                        Thread.sleep(7000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程here1");
                    try {

                        DeadLock2.class.wait();    //会将锁释放
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程here2");
                }
            }
        };
        th1.start();
    }


}
