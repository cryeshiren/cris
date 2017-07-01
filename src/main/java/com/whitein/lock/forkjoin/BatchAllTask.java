package com.whitein.lock.forkjoin;

import java.lang.reflect.Method;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by CR on 2017/7/1.
 * fork/join 执行批量任务
 * 基于RecursiveAction RecursiveTask ForkJoinPool
 */
public class BatchAllTask {
    private Class[] batch;
    public BatchAllTask( Class[] clazz ){
        this.batch = clazz;
    }
    public void excuteBatch(){
        ForkJoinPool fjp = new ForkJoinPool();
        BatchTask bt = new BatchTask(0, batch.length);
        fjp.submit(bt);
    }

    class BatchTask extends RecursiveAction{
        private int start;
        private int end;
        private final int THRESHOLD = 2;
        public BatchTask( int start, int end ){
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            boolean canCompute = (end - start)>THRESHOLD?false:true;

            if( canCompute ){
                for( int i = start; i<end; i++ ){
                    try {
                        Object obj = batch[i].newInstance();
                        Method method = batch[i].getMethod("excute");
                        method.invoke(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else{
                int middle = (start+end)/2;
                BatchTask leftBt = new BatchTask(start, middle);
                BatchTask rightBt = new BatchTask(middle, end);
                //执行子任务
                leftBt.fork();
                rightBt.fork();
            }
        }
    }
}
