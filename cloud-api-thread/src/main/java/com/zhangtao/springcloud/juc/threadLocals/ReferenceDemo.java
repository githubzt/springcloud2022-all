package com.zhangtao.springcloud.juc.threadLocals;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class MyObject{
    /**这个方法一般不用复写，只是为了教学演示案例说明**/
    @Override
    protected void finalize() throws Throwable {
        //finallize的通常目的是在对象不可撤销的丢弃之前执行清理操作
        System.out.println("----invoke finalize method~~~~!!!!");
    }
}
/**
 * 1. @ClassDescription:  新建一个带finalize()方法的对象MyObject  演示 强引用、软引用、弱引用、虚引用分别是什么？
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月10日 17:32
 */
public class ReferenceDemo {

    public static void main(String[] args) {
        /**强引用**/
        strongReference();
        /**软引用*/
        softReference();
        /**弱引用**/
        weekReference();
        /**虚引用**/
        phantomReference();
    }
    public static void phantomReference() {
        /**虚引用  需要用到java.lang.PhantomReference 形同虚设 不会决定对象的声明周期 任何时候都可能被垃圾回收器回收
         * 1、虚引用必须和队列(ReferenceQueue)联合使用 2、phantomReference的get方法总是返回null 3、处理监控通知使用
         *  我被回收前需要被引用队列保存下
         * **/
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
        //模拟内存不够用  run-Edit Configurations-Application--VM options -Xms10m -Xmx10m
        System.out.println("虚引用 " + phantomReference.get());

        ArrayList<Object> list = new ArrayList<>();
        new Thread(()->{
            while (true){
                list.add(new byte[1*1024*1024]);
                //暂停500毫秒
                try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("虚引用 " + phantomReference.get() + "\t list add ok");
            }
        },"t1").start();
        new Thread(()->{
            while (true){
                Reference<? extends MyObject> poll = referenceQueue.poll();
                if(poll!=null){
                    System.out.println("------有虚对象回收加入了队列！");
                    break;
                }
            }
        },"t2").start();

    }


    public static void weekReference() {
        /**弱引用  需要用到java.lang.WeekReference类来实现，它比软应用生存期更短
         *   只要垃圾回收机制一运行，不管jvm的内存是否足够，都会回收该对象占用的内存
         * 软引用和弱引用适用场景： 读取大量图片
         * 设计思路： 用一个HashMap来保存图片路径和相应图片对象关联的软引用之间的映射关系，在内存不足时，JVM会自动回收这些缓存图片对象所占用的空间，
         * 从而有效的避免了OOM的问题 Map<String,SoftReference<Bitmap> image = new HashMap<String,SoftReference<Bitmap>>();
         * **/

        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("gc before:-------weakReference: " + weakReference.get());
        System.gc();
        //暂停1秒
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("gc after:---weakReference: " + weakReference.get());


    }
    public static void softReference() {
        /**软引用   一种相对强引用弱化了一些的引用，需要java.lang.ref.SoftReference类来实现，可以让对象豁免一些垃圾收集
         *   当系统充足时 不会 被回收； 系统内存不足时 会 被回收。
         *   高速缓存用到软引用
         * **/
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("gc before:-------softReference: " + softReference.get());
        System.gc();
        //暂停1秒
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("gc after:--内存够用-softReference: " + softReference.get());

        //模拟内存不够用  run-Edit Configurations-Application--VM options -Xms10m -Xmx10m
        try {
            byte[] bytes = new byte[20 * 1024 * 1024];//20MB对象
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("gc after:--内存不够用-softReference: " + softReference.get());
        }
    }

    public static void strongReference() {
        /**强引用  new XXX(); **/
        MyObject myObject = new MyObject();
        System.out.println("gc before: " + myObject);
        myObject = null;
        System.gc(); //人工开启GC 一般不用
        //暂停1秒
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("gc after: " + myObject);
    }
}