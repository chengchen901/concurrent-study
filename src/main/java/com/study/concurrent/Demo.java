package com.study.concurrent;

import java.util.Random;

public interface Demo {

    default int test1() {
//        return new Random(1).nextInt();
        return test2();
    }

    static int test2() {
        return new Random(2).nextInt();
    }
}
