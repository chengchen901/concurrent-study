package com.study.concurrent.period5;

// 锁粗化(运行时 jit 编译优化)
// jit 编译后的汇编内容, jitwatch可视化工具进行查看
public class Demo5_LockCoarsening {
    int i;

    public void test1(Object arg) {
        for (int j = 0; j < 10000; j++) {
            synchronized (this) {
                i++;
            }
        }
        // 发现只有一个线程，优化后
        synchronized (this) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

}
