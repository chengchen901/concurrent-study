package com.study.concurrent.period1;

public class Demo2_ThreadState {
    public static Thread thread1;
    public static Demo2_ThreadState obj;

    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() throws InterruptedException {
        // 第一种状态切换 - 新建 -> 运行 -> 终止
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程发来贺电。。。");
            }
        });

        System.out.println("1、调用start方法前，thread1状态：" + thread1.getState().toString());
        Thread.sleep(2000L);
        thread1.start();
        System.out.println("2、调用start方法后，thread1状态：" + thread1.getState().toString());

        Thread.sleep(4000L); // 等待thread1执行结束，再看状态
        System.out.println("3、2s后，thread1状态：" + thread1.getState().toString());
        //thread1.start();     //TODO 注意，线程终止之后，再进行调用，会抛出IllegalThreadStateException异常


    }

    public static void test2() throws InterruptedException {
        System.out.println("############第二种：新建 -> 运行 -> 等待 -> 运行 -> 终止(sleep方式)###########################");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {// 将线程2移动到等待状态，1500后自动唤醒
                    Thread.sleep(5000);
                    System.out.println("3、Sleep结束," + Thread.currentThread().getName() + "当前状态：" + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
        System.out.println("1、调用start后，thread2状态：" + thread2.getState().toString());

        Thread.sleep(2000L); // 等待200毫秒，再看状态
        System.out.println("2、等待2s后，thread2状态：" + thread2.getState().toString());

    }

    public static void test3() throws InterruptedException {
        System.out.println("############第三种：新建 -> 运行 -> 阻塞 -> 运行 -> 终止###########################");

        //创建thread3，暂不启动
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2、抢锁之前，thread3状态：" + Thread.currentThread().getState().toString());
                synchronized (Demo2_ThreadState.class) {
                    System.out.println("5、thread3，拿到锁，继续执行，thread3状态" + Thread.currentThread().getState());
                }
            }
        });
        

        synchronized (Demo2_ThreadState.class) {
            System.out.println("1、主线程拿到锁，启动thread3");
            thread3.start();
            Thread.sleep(5000L);
            System.out.println("3、thread3的状态" +  thread3.getState());
            Thread.sleep(24000L);
        }
        System.out.println("4、主线程释放锁");

    }

    public static void test4() throws InterruptedException {
        System.out.println("############第四种：新建 -> 运行 -> 等待 -> 运行 -> 终止###########################");
        Thread thread1 = new Thread(()->{
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(()->{
            thread1.start();
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("1、调用start方法前，thread2状态：" + thread2.getState().toString());
        Thread.sleep(2000L);
        thread2.start();
        System.out.println("2、调用start方法后，thread2状态：" + thread2.getState().toString());

        Thread.sleep(2000L);
        System.out.println("3、2s后，thread2状态：" + thread2.getState().toString());

        Thread.sleep(5000L);
        System.out.println("4、5s后，thread2状态：" + thread2.getState().toString());
    }

}
