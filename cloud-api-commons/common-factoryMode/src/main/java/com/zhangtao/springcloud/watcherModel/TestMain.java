package com.zhangtao.springcloud.watcherModel;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 15:25
 */
public class TestMain {

    public static void main(String[] args) {
        CompanyObserver companyObserver = new CompanyObserver();
        PersonObserver personObserver = new PersonObserver();
        Subject subject = new Subject();
        subject.addObserver(companyObserver);
        subject.addObserver(personObserver);
        subject.change(2);
        System.out.println("-----------------------");
        subject.change(0);
    }
}