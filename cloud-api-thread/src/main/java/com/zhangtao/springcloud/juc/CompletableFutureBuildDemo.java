package com.zhangtao.springcloud.juc;

import java.util.concurrent.*;

/**
 * 1. @ClassDescription:  jdk1.8  CompletableFuture
 *     四个静态方法，创建一个异步任务。  runAsync 无返回值  supplyAsync 有返回值
 *     Excutor excutor 参数说明：没有指定Excuotr的方法，直接使用默认ForkJoinPool.commonPool()作为他的线程池执行异步代码，指定了就用指定的。
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月13日 14:29
 */
public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //不指定线程池
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        });
        //指定线程池
        CompletableFuture<Void> comFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        }, threadPool);
        System.out.println(completableFuture.get());

        /**  有返回值的  */
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync";
        },threadPool);
        System.out.println("有返回值的：" + supplyAsync.get());
        threadPool.shutdown();
    }

}