package com.zhangtao.springcloud.juc.cas;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

@NoArgsConstructor
@AllArgsConstructor
@Data
class Book{
    private int id;
    private String bookname;
}
/**
 * 1. @ClassDescription:  解决单线程  ABA问题实例  版本号 ，戳记流水
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月08日 11:15
 */
public class AtomicStampedDemo {

    public static void main(String[] args) {
        Book javabook = new Book(1, "javabook");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javabook,1);
        System.out.println(stampedReference.getReference() +"\t"+ stampedReference.getStamp());

        Book mysqlbook = new Book(2, "mysqlbook");
        boolean b;
        b = stampedReference.compareAndSet(javabook,mysqlbook,stampedReference.getStamp(),
                stampedReference.getStamp()+1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        b = stampedReference.compareAndSet(mysqlbook,javabook,stampedReference.getStamp(),
                stampedReference.getStamp()+1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

    }
}