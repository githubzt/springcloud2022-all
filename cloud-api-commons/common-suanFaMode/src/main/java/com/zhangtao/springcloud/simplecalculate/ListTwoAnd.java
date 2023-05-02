package com.zhangtao.springcloud.simplecalculate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * 1. @ClassDescription:  list 的三种求和方式  stream
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 18:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Student{
    int id;
    String name;
    int age;

}

public class ListTwoAnd {

    public static void main(String[] args) {
        ArrayList<Student> stulist = new ArrayList<>();
        Student student = new Student(1, "张三", 28);
        Student student1 = new Student(2, "李四", 30);
        Student student2 = new Student(3, "王二", 20);
        stulist.add(student);stulist.add(student1);stulist.add(student2);

        System.out.println("++++++++++++++++++++++++方式一++++++++++++++++++++++++++++++");
        int suma = stulist.stream().map(e -> {return e.getAge();}).reduce(Integer::sum).get();
        System.out.println(suma);
        int maxa = stulist.stream().map(e -> e.getAge()).reduce(Integer::max).get();
        System.out.println(maxa);
        int mina = stulist.stream().map(e -> e.getAge()).reduce(Integer::min).get();
        System.out.println(mina);
        System.out.println("++++++++++++++++++++++++方式二++++++++++++++++++++++++++++++");

        double sum = stulist.stream().mapToDouble(Student::getAge).sum();
        System.out.println(sum);
        int asInt = stulist.stream().mapToInt(Student::getAge).max().getAsInt();
        System.out.println(asInt);
        int asInt1 = stulist.stream().mapToInt(Student::getAge).min().getAsInt();
        System.out.println(asInt1);


    }

}