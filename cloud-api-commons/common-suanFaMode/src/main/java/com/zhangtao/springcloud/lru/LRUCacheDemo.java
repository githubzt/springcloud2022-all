package com.zhangtao.springcloud.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 1. @ClassDescription: 依赖JDK  LinkedHashMap 实现LRU(最近最少使用)算法
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月06日 14:22
 */
public class LRUCacheDemo<K,V> extends LinkedHashMap{
    /**缓存坑位**/
    private int capacity;

    /**
    *  accessOrder the order mode   <tt>true</tt> for access-order,  按照访问顺序插入，去除
     *  <tt>false</tt> for insertion-order  按照插入顺序，同一个key位置不变
    */
    public LRUCacheDemo(int capacity){
        super(capacity,0.75F,true);
        this.capacity=capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1,"a");
        lruCacheDemo.put(2,"b");
        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(4,"d");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());
    }



}