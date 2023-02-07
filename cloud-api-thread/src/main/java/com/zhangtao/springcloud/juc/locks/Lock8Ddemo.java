package com.zhangtao.springcloud.juc.locks;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月31日 16:07
 */

import java.util.concurrent.TimeUnit;

/***资源类****/
class Phone{
    public /*static*/ synchronized  void  sendEmail(){
        //暂停毫秒
        //try {TimeUnit.MILLISECONDS.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("-----sendEmail");
    }
    public /*static*/ synchronized void sendSMS(){
        System.out.println("------sendSMS");
    }

    public void hello(){
        System.out.println("-----hello");
    }
}
/** 题目： 谈谈你对多线程锁的理解额 8锁案例说明
 *  口诀： 线程 操作 资源类
 * 8锁案例说明 ： 1、标准访问ab俩个线程，请问先打印邮件还是短信  邮件
 *  2、sendEmail方法中加入暂停3秒种，请问先打印邮件还是短信 邮件
 *  3、添加一个普通的hello方法，请问先打印邮件还是hello   hello
 *  4、有俩部手机，请问先打印邮件还是短信  短信
 *  5、有俩个静态同步方法，有1部手机，请问先打印邮件还是短信 邮件
 *  6、有俩个静态同步方法，有2部手机，请问先打印邮件还是短信 邮件
 *  7、有1静态同步方法,一个普通同步方法，有1部手机，请问先打印邮件还是短信 短信
 *  8、有1静态同步方法,一个普通同步方法，有2部手机，请问先打印邮件还是短信 短信
 *
 * 总结：
 *  1-2 : 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中一个synchronized方法，其它线程只能等待，锁的是当前对象this
 *       ，被锁定后，其它线程都不能进去到当前对象的其它synchronized方法。
 *  3-4 : 普通方法和同步方法无关， 换成俩个对象就是俩把锁，不产生争夺
 *  5-6 :  静态方法的情况变化
 *    普通同步方法 锁的是当前实例的对象 即“对象锁”，通常指this,
 *    静态同步方法 锁的是当前类的Class对象即 “类锁”，如Phone.class唯一的一个模板
 *    同步方法快 锁的是synchronized括号中的对象
 * 7-8 :  实例对象this和唯一模板Class,这俩把锁是俩个不同的对象，所以静态同步方法与普通的同步方法之间是不会有竞争条件的，但是一旦一个静态同步方法
 *       获取锁之后，其它静态同步方法都必须等待该方法释放锁之后才能获取锁。
 */

public class Lock8Ddemo {
    //一切程序入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            phone.sendEmail();
        },"a").start();
        //暂停毫秒 保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            phone.sendSMS();
           //phone.hello();
           //phone2.sendSMS();
        },"b").start();


    }


}