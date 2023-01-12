package com.zhangtao.springcloud;

import java.util.Date;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月10日 17:06
 */
public class TestWorker {

    public static void main(String[] args) {
        //获取当前时间戳
        Date date = new Date();
        System.out.println(date.getTime());

        IDWorker idWorker = new IDWorker(0,0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            System.out.println("\n" +Long.toBinaryString(id));
            System.out.println(id);
        }

    }

}