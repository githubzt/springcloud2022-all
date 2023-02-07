package com.zhangtao.springcloud.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:  俩个CompletionStage任务都完成后，最终能把俩个任务结果一起交给thenCombine来处理，先完成的先等着。
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月31日 14:22
 */
public class CompletableFutureThenCombineDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---启动2");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        CompletableFuture<Integer> combine = future1.thenCombine(future2, (x, y) -> {
            System.out.println("开始合并俩个结果");
            return x + y;
        });

        System.out.println(combine.get());




    }

}