package com.whitein.threadpool;

/**
 * Created by CR on 2017/6/29.
 */
public interface ThreadPool< Job extends Runnable > {

    /**
     * 任务执行方法
     * @param job 任务
     */
    void execute( Job job );

    /**
     * 停止任务
     */
    void shutDown();

    /**
     *
     * @param num 线程池数量
     */
    void addWorkers( int num );

    /**
     *  移除任务消费者
     * @param num 移除数量
     */
    void removeWorker( int num );

    /**
     * 获取任务个数
     * @return 任务个数
     */
    long getJobSize();
}
