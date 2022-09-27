package com.study.concurrent.period6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Demo5_ReadWrite01 {

    volatile long i = 0;
    volatile long j = 0;

//    Lock lock = new ReentrantLock();
    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void read() {
        rwLock.readLock().lock();
        System.out.println("i=" + i + ",j=" + j);
        rwLock.readLock().unlock();

    }

    public void write() {
        rwLock.writeLock().lock();
        i++;
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        j++;
        rwLock.writeLock().unlock();
    }

    public static void main(String[] args) {
        final Demo5_ReadWrite01 demo = new Demo5_ReadWrite01();
        for (int i = 0; i < 10; i++) {
            int n = i;
            new Thread(()->{
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 2000) {
                    if (n == 0) {
                        demo.write();
                    } else {
                        demo.read();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.i);
    }
}
