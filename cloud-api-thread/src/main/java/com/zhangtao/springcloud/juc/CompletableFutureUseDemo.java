package com.zhangtao.springcloud.juc;

import java.util.concurrent.*;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月13日 14:57
 */
public class CompletableFutureUseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //案例一  主动获取异步任务值
        future1();
        //案例二 异步任务值主动通知
        future2();

    }

    public static void future2() {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "---- come in ");
                int anInt = ThreadLocalRandom.current().nextInt(10);
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("----1秒钟后出结果： " + anInt);
                //模拟异常情况
                if(anInt > 2){
                    int i = 10/0;
                }
                return anInt;
            },threadPool).whenComplete((v,e)->{ // v 上一步结果   e上一步是否报错
                if(e==null){
                    System.out.println("-----计算完成，更新系统UpdateVa: " + v);
                }
            }).exceptionally((e)->{
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\n " + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + "-----忙其它任务了");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
        //ForkJoinPool类似手护线程 主线程结束它立刻结束。 所以要使用自定以线程池
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭： 暂停3秒钟
        //try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
    }

    public static void future1() throws InterruptedException, ExecutionException {

        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---- come in ");
            int anInt = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----1秒钟后出结果： " + anInt);
            return anInt;
        });
        System.out.println(Thread.currentThread().getName() + "-----忙其它任务了");
        //主动获取异步任务的值
        System.out.println(supplyAsync.get());

    }
}