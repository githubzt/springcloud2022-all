package com.zhangtao.springcloud.util;

import com.zhangtao.springcloud.controller.service.ConsumerOneParam;
import com.zhangtao.springcloud.controller.service.ConsumerTwoParam;
import com.zhangtao.springcloud.controller.service.ForLambda;
import com.zhangtao.springcloud.entities.FiOrder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * 1. @ClassDescription:  练习使用lambda  只有函数式接口的匿名内部类才可以使用Lambda表达式来进行简化。
 *     函数式接口 ：只有一个 抽象方法的接口 称之为 函数式接口。函数式接口可以使用@FunctionalInterface进行注解。
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月12日 9:34
 */

public class LambdaTest {

    /**
     *Lambda表达式是JAVA8中提供的一种新的特性，是一个匿名函数方法。可以把Lambda表达式理解为一段可以传递的代码，可以写出更简洁、更灵活的代码。
     *函数式接口不同于普通接口，较为特殊化。接口当中只有一个抽象方法是需要我们去实现的，Lambda表达式正好是针对这个唯一的抽象方法使用。
     *
     *线程创建的两种方式：继承Thread方式、实现Runnable方式。
     *     * Runable是一个接口，里面只有一个抽象方法是需要我们强制实现的，该接口就是一个函数式接口。
     */
    public static void main(String[] args) {
        //匿名内部方式创建线程
        Thread t1 = new Thread(new Runnable() {
            @Override //这里重写Runnable接口里面的run()方法
            public void run() {
                System.out.println("匿名内部类方式--启动线程。");
            }
        });
        t1.start();

        //Lambda表达式创建线程 无参数，无返回值
        Thread t2 = new Thread(() -> System.out.println("匿名内部类方式-lambda--启动线程。"));
        t2.start();
        //有一个参数，无返回值
        ConsumerOneParam<java.lang.String> con = x -> System.out.println(x);
        con.accept("HAHAAHAHAH");
        /**
        *Lambda表达式就是对函数式接口中抽象方法的实现，是对其匿名内部类的一个简写，
        * 只保留了方法的参数列表和方法体，其他的成分可以省略。因此，Lambda表达式的格式非常简洁，只有三部分组成：
        *  参数列表  箭头 方法体  总结：(参数列表)->{方法体}
        *
        * 在Lambda标准格式的基础上，使用省略写法的规则为：小括号内参数的参数类型可以省略。
        *    小括号有且只有一个参数，则小括号可以直接省略。
        *    如果大括号有且只有一个语句，无论是否有返回值，大括号、return关键字、分号可以省略。
        */

        /**
         * int cal(int a)；抽象方法当中，小括号当中只有一个参数，这里省略5的小括号，用n指代前面的5,进行接下来的操作
         */
        method(5,n -> {return ++n;});

        //创建一个Person类 Arrays遍历
        FiOrder[] fiOrders = new FiOrder[4];
        fiOrders[2] = new FiOrder(3L,"0001","U002","汽车",12, BigDecimal.valueOf(100000002),0,1,new Date(),new Date());
        fiOrders[3] = new FiOrder(4L,"0001","U002","汽车",13, BigDecimal.valueOf(100000003),0,1,new Date(),new Date());
        fiOrders[0] = new FiOrder(1L,"0001","U002","汽车",10, BigDecimal.valueOf(100000000),0,1,new Date(),new Date());
        fiOrders[1] = new FiOrder(2L,"0001","U002","汽车",11, BigDecimal.valueOf(100000001),0,1,new Date(),new Date());

        //将Persons放进Arrays.sort()方法当中，两个对象之间对比，进行升序排序
        //如果大括号有且只有一个语句，无论是否有返回值，大括号、return关键字、分号可以省略。
        //标准格式
        Arrays.sort(fiOrders, new Comparator<FiOrder>() {
            @Override
            public int compare(FiOrder o1, FiOrder o2) {
                return o1.getOrderCount()-o2.getOrderCount();
            }
        });

        //简略格式
        Arrays.sort(fiOrders, (one,two) -> one.getOrderCount() - two.getOrderCount());

        for (FiOrder order : fiOrders) {
            System.out.println(order.toString());
        }

        //有两个以上的参数，有返回值，并且lambda体中有多条语句
        ConsumerTwoParam<Integer> com = (x,y) -> {
            System.out.println("hello two paramters");
            return (x < y) ? -1 : ((x.equals(y)) ? 0 : 1);
        };
        System.out.println(com.compare(3, 2));

        /** 四大核心函数式接口
         * Consumer<T> : 消费性接口 void accept(T t);
         * Supplier<T> : 共给性接口 T get();
         * Function<T,R> : 函数性接口 T代表参数，R代表返回值 R apply(T t);
         * Predicate<T> :断言性接口 boolean test(T t);
         *
         * lambda方法引用
         *  方法引用：若lambda体中的内同有方法已经实现了，我们可以使用“方法引用”
         * （可以理解为方法引用时lambda的另一种表现形式）
         * 主要有三种语法格式：  对象::实例方法名   类::静态方法名  类::实例方法名
         */
        // 对象::实例方法名
        ConsumerOneParam<String> conStr = (x) -> System.out.println(x);
        conStr.accept("对象::实例方法名");
        ConsumerOneParam<String> conStr1 = System.out::println;
        conStr1.accept("对象::实例方法名1111");

        // 类::静态方法名
        Comparator<Integer> com1 = (x,y) -> Integer.compare(x,y);
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com1.compare(1,2) + "\t" +com2.compare(3,4));

        // 类::实例方法名
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);
        System.out.println("类::实例方法名 : "+bp.test("a", "a"));
        BiPredicate<String,String> bp2 = String::equals;
        System.out.println("类::实例方法名2 : " +bp2.test("a", "b"));

        //lambda构造器引用 CalssName::new
        Supplier<String> sup = () ->new String();
        /**这里的构造器引用取决于 接口方法的参数 的个数。 此处函数式接口 T get(); 为无参抽象方法所以String在实例化时
         * 也是实例化无参的构造方法  其他类也适用
         */
        Supplier<String> sup2 = String::new;
        System.out.println("lambda构造器引用: "+sup2.get());
    }

    public static void method(int num, ForLambda forLambda){
        //method 方法当中传入一个int类型数字，和一个计算机接口
        int result = forLambda.cal(num);
        System.out.println("result: " + result);
        System.out.println("result1: "+ result);
    }

}