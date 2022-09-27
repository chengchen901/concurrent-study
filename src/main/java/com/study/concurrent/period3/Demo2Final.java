package com.study.concurrent.period3;

import java.io.IOException;

/**
 * @author Hash
 * @since 2021/4/22
 */
public class Demo2Final {

    final int x;
    int y;

    static Demo2Final f;

    public Demo2Final(){
        x = 3;
        y = 4;
    }

    static void writer(){
        f = new Demo2Final();
    }

    static void reader(){
        if (f!=null){
            int i = f.x;        //一定读到正确构造版本
            int j = f.y;        //可能会读到 默认值0
            System.out.println("i=" + i + ", j=" +j);
        }
    }


    public static void main(String args[]) throws InterruptedException, IOException {
        // Thread1 writer
        new Thread(()->{
            Demo2Final.writer();
        }).start();
        // Thread2 reader
        new Thread(()->{
            Demo2Final.reader();
        }).start();
        System.in.read();
    }

}
