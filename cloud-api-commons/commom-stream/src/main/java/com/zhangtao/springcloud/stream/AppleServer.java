package com.zhangtao.springcloud.stream;

import com.zhangtao.springcloud.stream.bean.Apple;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年04月07日 9:52
 */
public class AppleServer {

    private static List<Apple> appleStore = new ArrayList<>();

    static {
        appleStore.add(new Apple(1,"red", BigDecimal.valueOf(500),"湖南"));
        appleStore.add(new Apple(2,"red", BigDecimal.valueOf(400),"湖南"));
        appleStore.add(new Apple(3,"green", BigDecimal.valueOf(300),"湖南"));
        appleStore.add(new Apple(4,"green", BigDecimal.valueOf(200),"天津"));
        appleStore.add(new Apple(5,"green", BigDecimal.valueOf(100),"天津"));
    }

    //找出红色的苹果
    public void test1(){
        for (Apple apple :appleStore){
            if(apple.getColor().equals("red")){
                System.out.println(apple.getId());
            }
        }
    }
    //过滤颜色
    public void test2(Predicate<? super Apple> pre){
        //方式一
        List<Apple> red = appleStore.stream().filter(a -> a.getColor().equals("red"))
                .filter(a->a.getWeight().compareTo(BigDecimal.valueOf(400))>0).collect(Collectors.toList());
        for (Apple apple : red) {
            System.out.println("方式一："+apple.getId());
        }
        //方式二  判断条件传参
        List<Apple> apples = appleStore.stream().filter(pre).collect(Collectors.toList());
        for (Apple apple : apples) {
            System.out.println("方式二："+ apple.getId());
        }

    }

    //求出每个颜色的平均重量  传统方法
    public void test3(){
        //1、基于颜色分组
        Map<String,List<Apple>>  maps = new HashMap<>();
        for (Apple apple : appleStore) {
            List<Apple> list = maps.computeIfAbsent(apple.getColor(), k -> new ArrayList<>());
            list.add(apple);
        }
        //2、遍历maps
        for (Map.Entry<String, List<Apple>> stringListEntry : maps.entrySet()) {
            BigDecimal weights = BigDecimal.valueOf(0);
            for (Apple apple : stringListEntry.getValue()) {
                weights = weights.add(apple.getWeight());
            }
            System.out.println(stringListEntry.getKey() +"颜色 平均重量: " + weights.divide(BigDecimal.valueOf(stringListEntry.getValue().size())));
        }
    }

    /**求出每个颜色的平均重量  stream**/
    public void test4(){
        appleStore.stream().collect(Collectors.groupingBy(a -> a.getColor(),
                Collectors.averagingDouble(a->a.getWeight().doubleValue())))
                .forEach((k,v)->System.out.println(k + " 颜色,平均重量 " + v));
    }





    public static void main(String[] args) {
        new AppleServer().test2(a->a.getColor().equals("red")&&a.getWeight().compareTo(BigDecimal.valueOf(400))>=0);
        new AppleServer().test3();
        new AppleServer().test4();

        //1.流的生成
        appleStore.stream();
        Arrays.stream(new int[]{1,2,3,4});
        Stream.of(1,2,3,4);
        //2、与不可重复
        Stream<Apple> stream = appleStore.stream();
        Stream<Apple> stream1 = stream.filter(a -> a.getColor().equals("red"));
        /**报错：stream has already been operated upon or closed**/
        //Stream<Apple> stream2 = stream.filter(a -> a.getWeight().compareTo(BigDecimal.valueOf(200)) >= 0);

        /** 上一个节点 可以影响下一个节点   peek(中间节点) forEach(终值节点)  执行一个函数
         * 影响方式： 1、过滤   2、转换
         * **/
        appleStore.stream().filter(a->a.getColor().equals("red"))
                .peek(a-> System.out.println(a.getColor() +" : "+ a.getWeight()))
                .toArray();
        //转换  去重
        appleStore.stream().filter(a->a.getColor().equals("red"))
                .map(a->a.getColor())
                .peek(color->System.out.println(color))
                .distinct()
                .peek(color->System.out.println("去重后: " + color))
                .toArray();
        /** 如何debug   调试窗口最后一个框 Trace Current Stream Chain**/

        /** 采集：1、list  2、map  3、group by 4、数组  5、求出最大值 6、求任意值 **/
        Map<String, Apple> map = appleStore.stream().collect(Collectors.toMap(a -> a.getColor(), a -> a, (a1, a2) -> a1));
        map.forEach((a,b)->System.out.println(a + " : " +b));
    }

}