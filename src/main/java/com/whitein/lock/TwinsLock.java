package com.whitein.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by CR on 2017/7/1.
 */
public class TwinsLock implements Lock {

    private static final class Sync extends AbstractQueuedSynchronizer{

        Sync( int count ){
            if( count<=0 ){
                throw new IllegalArgumentException(" arguments is invalid ");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for(;;){
                int current = getState();
                int newCount = current-arg;
                if( newCount < 0 || compareAndSetState(current, newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;){
                int current = getState();
                int newCount = current+arg;
                if( compareAndSetState(current, newCount) ){
                    return true;
                }
            }
        }
    }
    private final Sync sync = new Sync(2);
    @Override
    public void lock() {
        //注意
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Test
    public void testTwinsLock(){
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while( true ){
                    lock.lock();
                    try {
                        Thread.sleep(1000l);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for( int i = 0;i<10; i++ ){
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }

        for (int i = 0; i<10; i++){
            try {
                Thread.sleep(1000l);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
