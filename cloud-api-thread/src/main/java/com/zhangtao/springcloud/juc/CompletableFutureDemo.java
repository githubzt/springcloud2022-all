package com.zhangtao.springcloud.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 1. @ClassDescription: future + 线程池异步多线程任务配合，能显著提高程序的执行效率。
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月13日 10:08
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws  Exception{
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        //获取返回值
        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("---come in call()");
        return "hello Callable";
    }
}