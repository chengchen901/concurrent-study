package com.study.concurrent.period6_1.locks3;


public class KodyReadWriteLock {

    CommonMask mask = new CommonMask();

    public void lock(){
        mask.lock();
    }

    public boolean tryLock(){
        return mask.tryLock(1);
    }

    public void unlock(){
        mask.unlock();
    }



    public void lockShared(){
        mask.lockShared();
    }

    public boolean tryLockSahred(){
        boolean result = mask.tryLockShared(1)==1;
        return result;
    }

    public void unLockShared(){
        mask.unLockShared();
    }



}
