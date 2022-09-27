package com.study.concurrent.period1;

public class Demo3_stop {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();

        Thread.sleep(1000);     // 休眠1秒，确保i变量自增成功
//        thread.stop(); // i=1 j=0，无法保证同步代码块里面数据的一致性，破坏了线程安全
         thread.interrupt(); // i=1 j=1

        while (thread.isAlive()) {}  // 确保线程已经终止
        thread.print();     // 输出结果
    }
}

class MyThread extends Thread {
    private int i = 0, j = 0;

    @Override
    public void run() {
        synchronized (this) {
            ++i;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++j;
        }
        System.out.println("锁释放。。。");
    }

    public void print() {
        System.out.println("i=" + i + " j=" + j);
    }
}

