package com.zhangtao.springcloud.java8Lambda;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年04月07日 9:51
 */
public class SimpleLambda {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello Lambada");
            }
        }).start();

        new Thread(() -> System.out.println("hello Lambada")).start();

        /**Multiple non-overriding abstract methods found in interface
         * com.zhangtao.springcloud.java8Lambda.SimpleLambda.myRun2**/
        //start(()->System.out.println("hello"));

        /**前置条件 1、 必须是函数式接口 2、参数传递  3、代码编写方式
         * 1、单行表达式 可以省略 return
         * 2、代码块
         * 3、静态方法引用
         * 4、普通方法引用
         */
       run((name,age)->String.format("name:%s age:%s",name,age));
       run((name, age)->{
           String name1 = name; int age1 = age;
           return "name : " + name1 + "age :" + age1;
       });
       /**静态方法引用**/
       run(SimpleLambda::doFormat);
       /**普通方法引用**/
       run(new SimpleLambda()::doFormat1);
    }

    private static String doFormat(String s, int i) {
        return "name : " + s + "age :" + i;
    }

    private  String doFormat1(String s, int i) {
        return "name : " + s + "age :" + i;
    }

    public interface Format{
        String run(String name,int age);
    }
    public static void run(Format r){
        r.run("张涛",30);
    }

    /**函数式接口只能有一个方法  Runnable有一个方法  myRun2也有一个不能支持lambada
     * myRun2是错误写法  **/
    public interface myRun2 extends Runnable {
        public void run2();
    }
    public static void start(myRun2 run2){
        new Thread(run2).start();
    }

    /** 注解函数式接口  @FunctionalInterface   如果要加方法 必须是默认方法 default
     *  2、默认方法除外  3、Object 下面的方法除外  --> 因为所有方法最终继承自Object方法
     * */
    @FunctionalInterface
    public interface myRun3 extends Runnable {
        public default void run2() {}
        @Override  //object 下的方法
        String toString();
        @Override //object 下的方法
        boolean equals(Object obj);
    }
}