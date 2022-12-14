package com.study.concurrent.period6_1.locks5;


public class Test {

    static KodyReadWriteLock rwLock = new KodyReadWriteLock();
    //static ReadWriteLock rwLock = new ReentrantReadWriteLock();

    static volatile int i =0;

    static void add(){
        i++;
    }

    public static void main(String args[]) throws InterruptedException {
        long startTime = System.currentTimeMillis();


        for (int a=1; a<=20000; a++){
            final int n = a;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (n%5 ==0){
                        rwLock.writeLock().lock();
                        add();
                        rwLock.writeLock().unlock();
                    }else{
                        rwLock.readLock().lock();
                        System.out.println("i=" +i);
                        int a = i;
                        rwLock.readLock().unlock();
                    }
                }
            }).start();
        }


        while (true){
            System.out.println("目前耗时：" + (System.currentTimeMillis()-startTime) /1000 + "s");
            Thread.sleep(1000L);
            System.out.println("i=" + i);

        }
    }


}
