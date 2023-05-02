package com.zhangtao.springcloud.simplecalculate;

/**
 * 1. @ClassDescription:  给定一个数m 求大于该数的最小2的n次幂 返回n。
 *         输入     -1    1    3     9     15
 *         输出      1     1    4    16    16
 *  1.　　|=  ：是按位或并赋值的意思。
 * 2. 　>>>  ：表示不带符号向右移动二进制数，移动后前面统统补 0。
 *
 *java中&叫做按位与，&&叫做短路与，它们的区别是：
 *  & 既是位运算符又是逻辑运算符，&的两侧可以是int，也可以是boolean表达式，当&两侧是int时，要先把运算符两侧的数转化为二进制数再进行运算，
 *  而短路与（&&）的两侧要求必须是布尔表达式。举例如下：
 *  12&5 的值是多少？答：12转成二进制数是1100（前四位省略了），5转成二进制数是0101，则运算后的结果为0100即4 这是两侧为数值时；
 *
 * @author: zhangtao
 * @date: 2023/2/28
 */

public class Mideng {

    public static void main(String[] args) {
        Mideng mideng = new Mideng();
        System.out.println(mideng.getN(6));
        System.out.println(mideng.MIN_POWER_TWO(6));

        System.out.println(  4&3  );
        System.out.println(  3&4  );
        System.out.println(  4&4  );
        System.out.println(  4&12  );
    }

    public int getN(int param){
        if(param<=0){
            //如果num<=0，直接返回2的0次幂
            return 1;
        }else if((param & (param-1))==0){
            //如果num是2的乘方，则直接返回num
            return param;
        }
        //找到大于以2为底num的对数最近的整数
        //例：num=7,以2为底7的对数为：2.807...，强转int再加1结果为：3
        int tmp = (int) (Math.log(param) / Math.log(2)) + 1;
        System.out.println(Math.log(param));
        System.out.println(Math.log(2));
        System.out.println(tmp);
        //2的3次方
        return (int) Math.pow(2, tmp);
    }
    /**
     * 这里的 |= 运算符表示「按位或赋值」，例如 a |= b 表示 a = a | b 。同理，这里的 n |= n >>> 1 则表示 n = n | (n >>> 1) 。
     * （符号 >>> 为「无符号右移」操作符）
     *
     * **/
    public int MIN_POWER_TWO(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

}