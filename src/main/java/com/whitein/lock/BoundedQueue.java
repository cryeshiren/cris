package com.whitein.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by CR on 2017/7/1.
 * 有界队列
 */
public class BoundedQueue<T> {
    //元素集合
    private Object[] items;
    //添加下表，移除下标，集合数量
    private int addIndex, removeIndex, count;
    //重入锁
    private Lock lock = new ReentrantLock();
    //集合不满的情况
    private Condition notFull = lock.newCondition();
    //集合不空的情况
    private Condition notEmpty = lock.newCondition();

    public BoundedQueue( int size ){
        items = new Object[size];
    }

    public void add( T t ){
        lock.lock();
        try{
            while( count == items.length ){
                notFull.await();
            }
            items[addIndex] = t;
            if( ++addIndex == items.length ){
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        }catch( Exception e ){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //从头部删除一个元素
    public T  remove(  ){
        lock.lock();
        try{
            while( count == 0 ){
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if( ++removeIndex == items.length ){
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        }catch ( Exception e ){
            e.printStackTrace();
            return null;
        }finally {
            lock.unlock();
        }
    }
}
