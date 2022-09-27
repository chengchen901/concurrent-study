package com.study.concurrent.period4_1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

public class Demo3_CallableTest {
    public static void main(String args[]){
        //可以拿到返回结果
        CallTask cTask = new CallTask();
        FutureTask fTask = new FutureTask(cTask);

        new Thread(fTask).start();




        try {
            System.out.println("进入get方法，阻塞等待结果。。。");
            String result = (String)fTask.get();

            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class CallTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        LockSupport.parkNanos(1000 * 1000 *1000 * 5L);
        return "Kody";
    }
}
