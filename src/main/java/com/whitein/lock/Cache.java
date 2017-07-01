package com.whitein.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by CR on 2017/7/1.
 * 基于读写锁与HashMap实现的Cache
 */
public class Cache {
    private ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();
    private Lock readLock = rrw.readLock();
    private Lock writeLock = rrw.writeLock();
    private Map<String, Object> cache = new HashMap<>();

    public Object get( String key ){
        readLock.lock();
        try{
            return cache.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public void set( String key, Object object ){
        writeLock.lock();
        try{
            cache.put(key, object);
        }finally {
            writeLock.unlock();
        }
    }

    public void clear(){
        writeLock.lock();
        try{
            cache.clear();
        }finally {
            writeLock.unlock();
        }
    }
}
