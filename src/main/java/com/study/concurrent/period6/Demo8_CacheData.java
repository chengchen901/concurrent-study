package com.study.concurrent.period6;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
缓存示例
这是ReentrantReadWriteLock 注释中给出的一个示例
用于构建一个缓存，该缓存在读取并使用值的时候，不允许修改缓存值

*/
public class Demo8_CacheData {

    public static void main(String[] args) {

    }
}

class TeacherInfoCache {
    static volatile boolean cacheValid;
    static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    static Object get(String dataKey) {
        Object data = null;

        rwl.readLock().lock(); // 读数据加读锁
        try {
            if (cacheValid) {
                data = Redis.data.get(dataKey);
            } else {
//                data = DataBase.queryUserInfo(); // 可能存在缓存雪崩的场景
                rwl.readLock().unlock(); // 加写锁前需要先释放读锁，如果不释放则无法获取到写锁
                rwl.writeLock().lock();
                try {
                    if (!cacheValid) {
                        data = DataBase.queryUserInfo();
                        Redis.data.put(dataKey, data);
                        cacheValid = true;
                    }
                    // 获取读锁进行锁降级，另一个语义是如果不加读锁，可能其它线程会抢到写锁，导致当前线程读取的数据和其它线程不同
                    rwl.readLock().lock();
                } finally {
                    rwl.writeLock().unlock();
                }
            }
            return data;
        } finally {
            rwl.readLock().unlock();
        }

    }
}

class DataBase{
    static String queryUserInfo() {
        return "name:zhangsan,age:18";
    }
}

class Redis {
    static Map<String, Object> data = new HashMap<>();
}
