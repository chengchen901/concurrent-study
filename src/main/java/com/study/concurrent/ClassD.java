package com.study.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassD {
        ClassB b;

        public static void main(String[] args) {
            System.out.println("xxx");
            final List<String> collect = new ArrayList<String>().stream().map(s -> {
                System.out.println(s);
                return s;
            }).collect(Collectors.toList());
        }
        class ClassE {
            ClassB b;
        }
        static class ClassZ {
            ClassB b;
        }
    }

    class ClassF {
        ClassB b;
    }
    class ClassG {
        ClassB b;
    }
    abstract class ClassH {
        ClassB b;
    }
    interface ClassI {
    }
