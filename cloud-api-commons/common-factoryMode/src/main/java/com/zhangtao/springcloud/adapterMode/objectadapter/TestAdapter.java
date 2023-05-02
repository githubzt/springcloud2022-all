package com.zhangtao.springcloud.adapterMode.objectadapter;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 17:11
 */
public class TestAdapter {
    public static void main(String[] args) {
        PowerAdapter powerAdapter = new PowerAdapter(new TypeA());
        powerAdapter.v5();
    }
}