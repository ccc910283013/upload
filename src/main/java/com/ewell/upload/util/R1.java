package com.ewell.upload.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class R1 implements Runnable{

    private int taskName;

    public R1(int taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("正在执行task "+taskName);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskName+"执行完毕");
    }
    public static void main(String[] args) {

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<>(5));
//        System.out.println("主线程start---------------");
//        for(int i=0;i<15;i++){
//            R1 myTask = new R1(i);
//            executor.execute(myTask);
//            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
//                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
//        }
//        System.out.println("主线程end---------------");
    }
}