package com.study.concurrent.period2.custom;

/**
 * @author Hash
 * @since 2021/4/20
 */
public class ThreadPoolTest {
    public static void main(String args[]){
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3, 6);

        for (int i=0; i<6; i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务正在执行中。。。");
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
