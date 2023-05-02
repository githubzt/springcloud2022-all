package com.zhangtao.springcloud.juc.atomicAPI;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月09日 9:44
 */
public class LongAdderAPIDemo {

    public static void main(String[] args) {
        //longAdder只能用来计算加法 从0开始
        LongAdder adder = new LongAdder();
        adder.increment();adder.increment();adder.increment();
        System.out.println(adder.sum());

        //方式一 LongAccumulator提供了自订的函数操作  包含加减乘除
        LongAccumulator longAccumulator = new LongAccumulator((x,y)->{
            return x+y;
        },10);
        //方式二 匿名内部类 LongAccumulator提供了自订的函数操作  包含加减乘除
        LongAccumulator longAccumulator1 = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 0);

        // x是初始值10， y是下面传入的值
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(3);
        System.out.println("方式一 "+longAccumulator.get());

        longAccumulator1.accumulate(1);longAccumulator1.accumulate(2);
        System.out.println("方式二 "+longAccumulator1.get());

    }
}