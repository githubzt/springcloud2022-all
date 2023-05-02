package com.zhangtao.springcloud.juc.cas;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@ToString
@AllArgsConstructor
class User{
    String userName;
    int age;
}

/**
 * 1. @ClassDescription:  原子引用  包装类
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月08日 9:53
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> reference = new AtomicReference<>();
        User z3 = new User("z3", 22);
        User l4 = new User("L4", 33);

        reference.set(z3);

        System.out.println(reference.compareAndSet(z3, l4) + "\t" + reference.get().toString());

    }
}