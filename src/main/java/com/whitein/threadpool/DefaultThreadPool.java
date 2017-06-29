package com.whitein.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by CR on 2017/6/29.
 */
public class DefaultThreadPool< Job extends Runnable > implements ThreadPool<Job> {
    //线程池最大限制数
    private final int MAX_WORKER_NUMBERS =  10;
    //线程池最小限制数
    private final int MIN_WORKER_NUMBERS = 1;
    //线程池默认大小
    private final int DEFAULT_WORKER_NUMBER = 5;
    //任务列表
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //消费者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    //线程编号
    private AtomicLong atomicLong = new AtomicLong();
    //消费者数量
    private int workerNumber = DEFAULT_WORKER_NUMBER;

    //线程池初始化
    public DefaultThreadPool( int num ){
        num = num>MAX_WORKER_NUMBERS?MAX_WORKER_NUMBERS:num<MIN_WORKER_NUMBERS?MIN_WORKER_NUMBERS:num;
        initializeWorkers(num);
    }
    public void execute(Job job) {
        if( job!=null ){
            synchronized (jobs) {
                //添加任务
                jobs.addLast(job);
                //唤起等待者
                jobs.notify();
            }
        }
    }

    public void shutDown() {
        for( Worker worker: workers ){
            worker.shutDown();
        }
    }

    public void addWorkers(int num) {
        synchronized (jobs){
            if( num + this.workerNumber>MAX_WORKER_NUMBERS ){
                num = MAX_WORKER_NUMBERS-this.workerNumber;
            }
            initializeWorkers(num);
            this.workerNumber+=num;
        }
    }

    public void removeWorker(int num) {
        synchronized (workers){
            if( num>this.workerNumber ){
                throw new IllegalArgumentException(" num beyond work num ");
            }
            int count = 0;
            while( count<num ){
                Worker worker = workers.get(count);
                if( workers.remove(worker) ){
                    worker.shutDown();
                    count++;
                }
            }
            this.workerNumber -= count;
        }
    }

    public long getJobSize() {
        return jobs.size();
    }

    /**
     * 初始化线程池
     * @param num 线程池大小
     */
    private void initializeWorkers(int num){
        //创建并启动消费者
        for ( int i=0; i<num; i++ ){
            Worker worker = new Worker();
            Thread thread = new Thread(worker, String.valueOf(atomicLong.incrementAndGet()));
            workers.add(worker);
            thread.start();
        }
    }

    /**
     * 消费者
     * 负责执行任务
     */
    class Worker implements Runnable{
        //消费者执行任务开关，线程同步
        private volatile boolean running = true;
        public void run() {
            while( running ){
                Job job = null;
                synchronized (jobs){
                    //如果任务列表为空则执行等待
                    if (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //主动中断消费者
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                            return;
                        }
                    }
                    //获取任务
                    job = jobs.removeLast();
                }
                if( job!=null ){
                    //执行任务
                    job.run();
                }
            }
        }

        public void shutDown(){
            running = false;
        }
    }
}
