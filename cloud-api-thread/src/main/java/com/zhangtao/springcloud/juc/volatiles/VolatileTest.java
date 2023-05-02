package com.zhangtao.springcloud.juc.volatiles;

/**
 * 1. @ClassDescription:  内存屏障
 *          读屏障(Load Barrier)： 读指令之前插入屏障，让工作内存或cpu高速缓存区缓存数据失效
 *          写屏障(Store Barrier)：写指令之后插入屏障，强制把写缓存区的数据刷回到主内存中
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月07日 11:20
 */
public class VolatileTest {

    int i=0;
    volatile boolean flag = false;

    public void write(){
        i=2;
        flag = true;
    }
    public void read(){
        if(flag){
            System.out.println("---i = " + i);
        }
    }

}