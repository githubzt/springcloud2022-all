package com.zhangtao.springcloud.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月30日 10:57
 */
public class CompletableFutureAPI1Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        //不见不散
        //System.out.println(uCompletableFuture.get());
        //过时不候
        //System.out.println(uCompletableFuture.get(2, TimeUnit.SECONDS));
        //不用方法中抛出异常,除此和get一样
        //System.out.println(uCompletableFuture.join());
        //不用方法中抛出异常, 计算完成给出计算值，没计算完成给自定义值
        System.out.println(uCompletableFuture.getNow("not Completable"));
        //是否打断get方法, 获取括号里的值返回
        System.out.println(uCompletableFuture.complete("返回该值") + "\t" +uCompletableFuture.join());

    }
}