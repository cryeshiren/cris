package com.whitein.lock.forkjoin;

import org.junit.Test;

/**
 * Created by CR on 2017/7/1.
 */
public class Task {
    public void excute(){
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void testBatchAllTask() throws InterruptedException {
        Class[] clazz = new Class[10];
        for( int i = 0; i<10; i++ ){
            clazz[i] = Task.class;
        }

        BatchAllTask bat = new BatchAllTask(clazz);
        bat.excuteBatch();

    }
}
