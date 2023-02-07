package com.zhangtao.springcloud.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:  对计算速度进行选择 ： 谁快用谁   applyToEither（）
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月31日 10:49
 */
public class CompletableFutureFastDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playA";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playB";
        });

        CompletableFuture<String> either = playA.applyToEither(playB, f -> {
            return f + "  is winer";
        });
        System.out.println(Thread.currentThread().getName() + "---\t" + either.get());
    }
}