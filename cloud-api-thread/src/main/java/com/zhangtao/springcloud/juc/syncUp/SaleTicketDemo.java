package com.zhangtao.springcloud.juc.syncUp;

/**资源类 模拟3个售票员买完50张票**/
class Ticket{
    private int number = 50;
    Object  obj = new Object();
    public void sale(){
        synchronized (obj){
            if(number>0){
                System.out.println(Thread.currentThread().getName() + "卖出第： " + (number--) + " 还剩下： " + number);
            }
        }
    }
}
/**
 * 1. @ClassDescription:  synchronized升级
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月14日 13:47
 */
public class SaleTicketDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //方法一
        /*ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 50; i++) {
            threadPool.submit(()->{
                ticket.sale();
            });
        }
        threadPool.shutdown();*/

        //方法二
        new Thread(()->{ for(int i=0;i<55;i++){ticket.sale();}},"a").start();
        new Thread(()->{for(int i=0;i<55;i++){ticket.sale();}},"b").start();
        new Thread(()->{for (int i = 0; i < 55; i++) {ticket.sale();}},"c").start();

    }
}