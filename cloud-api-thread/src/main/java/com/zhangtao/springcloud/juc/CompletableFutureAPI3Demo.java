package com.zhangtao.springcloud.juc;

import java.util.concurrent.CompletableFuture;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月30日 15:09
 */
public class CompletableFutureAPI3Demo {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->{
            return 1;
        }).thenApply(f->{
            return f+2;
        }).thenApply(f->{
            return f+3;
        }).thenAccept(System.out::println);
        //.thenAccept(r->{System.out.println(r);});  写法二

        /**
         * thenRun(Runnable run)   任务A执行完执行B,并且B不需要A的结果
         * thenAccept(Consumer action) 任务A执行完执行B,B需要A的结果，但是任务B没有返回值
         * thenApply(Function fn)  任务A执行完执行B,B需要A的结果，同时任务B有返回值
         */
        System.out.println(CompletableFuture.supplyAsync(() -> {return "结果A";}).thenRun(() -> {}).join());
        System.out.println(CompletableFuture.supplyAsync(() -> {
            return "结果A";
        }).thenAccept(r -> {
            System.out.println(r);
        }).join());

        System.out.println(CompletableFuture.supplyAsync(()->{return "结果A";}).thenApply(r->{ return r+"结果B";}).join());
    }
}