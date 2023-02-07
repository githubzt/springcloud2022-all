package com.zhangtao.springcloud.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月13日 10:58
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws Exception{
        //方式1     3个任务 目前只有一个线程main来处理 请问耗时？
        test1();
        //方式2    3个任务 目前开启多个异步任务线程来处理 请问耗时？
        test2();

    }

    public static void test2() throws Exception{
        //方式2    3个任务 目前开启多个异步任务线程来处理 请问耗时？
        long startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> futureTask1 = new FutureTask<String>(()->{
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
            return "task1 over";
        });
        threadPool.submit(futureTask1);
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            return "task2 over";
        });
        threadPool.submit(futureTask2);
        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
            return "task3 over";
        });
        threadPool.submit(futureTask3);
        System.out.println(futureTask1.get() + "\n" + futureTask2.get() + "\n" +futureTask3.get());

        threadPool.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime：" + (endTime - startTime) + "毫秒");
        System.out.println("当前线程名称：" + Thread.currentThread().getName() + "\t ----end");
    }

    public static void test1(){
        // 3个任务 目前只有一个线程main来处理 请问耗时？
        long startTime = System.currentTimeMillis();
        //暂停毫秒数
        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime：" + (endTime - startTime) + "毫秒");
        System.out.println("当前线程名称：" + Thread.currentThread().getName() + "\t ----end");
    }


}