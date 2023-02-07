package com.zhangtao.springcloud.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 1. @ClassDescription: 模拟get()阻塞
 *   1、get 容易堵塞 不见不散 非要等到结果才离开，不管你是否计算完成 容易程序堵塞。
 *   2、不停的轮询获取状态，进而获取结果 耗费CPU资源。
 *   3、对结果获取不是很友好，只能通过阻塞或者轮询的方式得到任务的结果。
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月13日 13:18
 */
public class FutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName()+ "\t ----come in ");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        //没有算完就获取结果
        //System.out.println(futureTask.get()); // 不见不散 非要等到结果才离开，不管你是否计算完成 容易程序堵塞

        System.out.println(Thread.currentThread().getName()+ "\t ----忙其它任务了");

        //System.out.println(futureTask.get());
        //假如我不愿意等待很长时间，我希望过时不候，可以自动离开。 没结果直接报错
        //System.out.println(futureTask.get(3,TimeUnit.SECONDS));

        while (true){  //不停的轮询获取状态，进而获取结果 耗费CPU资源
            if(futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在处理中，不要再催了，越催越慢，再催熄火");
            }
        }


    }
}