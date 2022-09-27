package com.study.concurrent.period1;

public class Demo5_ThreadLocal {
    /** threadLocal变量，每个线程都有一个副本，互不干扰 */
    public static ThreadLocal<String> value = new ThreadLocal<>();


    public void threadLocalTest() throws Exception {

        // threadlocal线程封闭示例
        value.set("123"); // 主线程设置值
        String v = value.get();
        System.out.println("主线程，v：" + v);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String v = value.get();
                System.out.println("子线程:线程1取到的值：" + v);

                // 设置 threadLocal
                value.set("456");

                v = value.get();
                System.out.println("子线程:重新设置之后，线程1取到的值：" + v);
                System.out.println("子线程 执行结束");
            }
        }).start();

        Thread.sleep(12000L); // 等待所有线程执行结束

        v = value.get();
        System.out.println("主线程，v：" + v);

    }

    public static void main(String[] args) throws Exception {
        new Demo5_ThreadLocal().threadLocalTest();
    }
}
