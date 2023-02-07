package com.zhangtao.springcloud.juc;

import java.util.concurrent.*;

/**
 * 1. @ClassDescription:  计算结果传递
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月30日 14:34
 */
public class CompletableFutureAPI2Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("买鱼");
            return 1;
        },threadPool).handle((f,e) ->{ //thenApply(f -> {
            int i = 10/0; //模拟异常
            System.out.println("炖鱼");
            return f + 2;
        }).handle((f,e)->{ //.thenApply(f -> {
            System.out.println("吃鱼");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        //自动打印结果 自己创建线程池
        System.out.println(Thread.currentThread().getName() + "--主线程先去忙其它任务");
        threadPool.shutdown();
        //主动获取结果
        // System.out.println(exceptionally.get());
    }
}