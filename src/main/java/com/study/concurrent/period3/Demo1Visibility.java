package com.study.concurrent.period3;

/**
 * javap -v -p Demo1Visibility.class 反编译class文件
 *
 * @author Hash
 * @since 2021/4/22
 */
public class Demo1Visibility {
    int i = 0;
    volatile boolean isRunning = true;

    public static void main(String args[]) throws InterruptedException {
        Demo1Visibility demo = new Demo1Visibility();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("here i am...");
                while(demo.isRunning){
                    demo.i++;
                }
                System.out.println("i=" + demo.i);
            }
        }).start();

        Thread.sleep(3000L);
        demo.isRunning = false;
        System.out.println("shutdown...");
    }
}
