package com.zhangtao.springcloud.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 1. @ClassDescription:  chain链式调用   get() 于 join()  区别  编译时是否报出检查异常
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月13日 16:33
 */
public class CompletableFutureMallDemo {

    /*  案例说明： 电商比价需求
     * 1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价
     * 1.2 同一款产品 同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
     * 2、输出 同款产品在不同的地方的价格清单列表，返回一个List<String>
     *    《myql》 in jd price is 88.05
     *    《myql》 in dangdang price is 100.05
     *    《myql》 in taobao price is 70.05
     *3、技术要求; 3.1 函数式编程  3.2链式编程  3.3 Stream流式编程
     */
    /**模拟电商平台**/
    static List<NetMall> list = Arrays.asList(new NetMall("jd"),new NetMall("dangdang"),
            new NetMall("taobao"));

    /** 流式编程   step by setp 一家家查
     *  List<NetMall> -->  map  -----> List<Stirng>
     * %s占位符  %.2f保留2位小数   */
    public static List<String> getPrice(List<NetMall> list,String productName){
        return list.stream().map(netMall -> String.format(productName + " in %s price is %.2f", netMall.getNetMallName(),
                netMall.calPrice(productName))).collect(Collectors.toList());

    }
    /** 流式编程  并发异步查
     * List<NetMall> -->List<CompletableFuture<String>  ---> List<Stirng>
     */
    public static List<String> getPriceByCompletable(List<NetMall> list,String productName){
        return list.stream().map(netMall -> CompletableFuture.supplyAsync(()-> String.format(productName + " in %s price is %.2f",
                 netMall.getNetMallName(),netMall.calPrice(productName)))) //Stream<CompletableFuture<String>>
                .collect(Collectors.toList()) //List<CompletableFuture<String>>
                .stream() // 二次映射开启异步  Stream<CompletableFuture<String>>
                .map(s ->s.join()) //  Stream<String>
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        //chain链式
        Student student = new Student();
        student.setId(1);student.setStudentname("荷花");student.setMajor("科学");
        student.setId(2).setStudentname("桃花").setMajor("自然");
        // get() 于 join()  区别  编译时是否报出检查异常
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "hello 1231";
        });
        // 报错 必须处理异常
        //System.out.println(future.get());
        //不报错 不用处理异常
        System.out.println(future.join());

        /********************* 项目开始 ******************************************/
        //一个一个查
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "mysql");
        for (String element : list1) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("---costTime: " + (endTime - startTime) + " 毫秒");
        //多线程并发
        long startTime2 = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletable(list, "mysql");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("---costTime: " + (endTime2 - startTime2) + " 毫秒");
    }
}

/**模拟电商***/
class NetMall{

    @Getter
    private String netMallName;

    public NetMall(String netMallName){
        this.netMallName=netMallName;
    }

    public double calPrice(String productName){
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}

        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}





@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
class Student{
    private Integer id;
    private String studentname;
    private String major;
}