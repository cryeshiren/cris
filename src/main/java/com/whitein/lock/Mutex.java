package com.whitein.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * Created by CR on 2017/7/1.
 * 独占锁 Mutex
 * 基于abstractQueueSynchronizor实现
 */
public class Mutex {
    //内部类，自定义同步器
    private class Sync extends AbstractQueuedSynchronizer{
        //判断是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            if( compareAndSetState(0, 1) ){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            if( getState() == 0 ){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition condition(){
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync();

    public void lock(){
        sync.acquire(1);
    }

    public boolean tryAcquire(){
        return sync.tryAcquire(1);
    }

    public void unlock(){
        sync.release(1);
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
}
