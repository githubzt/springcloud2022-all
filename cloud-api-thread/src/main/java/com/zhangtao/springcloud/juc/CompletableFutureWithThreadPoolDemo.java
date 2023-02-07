package com.zhangtao.springcloud.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:  CompletableFuture和线程池说明 以thenRun和thenRunAsync为例，有什么区别？
 *      其它 thenAccept和thenAcceptAsync……  * 区别相同
 *          1、没有传入自定义线程池，都默认使用ForkJoinPool
 *          2、传入了一个自定义线程池 ： 2.1 调用thenRun方法执行第二个任务，则第二个任务和第一个任务是共用同一个线程池。
 *              2.2 调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个用的forkJoinPool线程池。
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月31日 10:12
 */
public class CompletableFutureWithThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "1";
            },pool).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRunAsync(() -> {
                try {TimeUnit.MILLISECONDS.sleep(40);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRunAsync(() -> {
                try {TimeUnit.MILLISECONDS.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });

            System.out.println(completableFuture.get(3L,TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}