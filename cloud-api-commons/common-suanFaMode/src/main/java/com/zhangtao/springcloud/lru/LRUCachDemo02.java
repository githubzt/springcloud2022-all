package com.zhangtao.springcloud.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. @ClassDescription: 不依赖JDK实现 LRU(最近最少使用)算法
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月06日 14:49
 */
public class LRUCachDemo02 {

    /**
     * Map负责查找，构建一个虚拟的双向链表，它里面安装的就是一个个Node节点，作为数据载体
     */
    /**1、构造一个Node节点，作为数据载体 **/
    class Node<K,V>{
        K key; V value;
        Node<K,V> prev;
        Node<K,V> next;

        public Node(){
            this.prev = this.next = null;
        }

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }
    /**2、构造一个双向队列，里面安放的就是我们的node **/
    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;
        /** 2.1 构造方法**/
        public DoubleLinkedList(){
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }
        /** 2.2 添加到头**/
        public void addHead(Node<K,V> node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
        /** 2.3 删除节点**/
        public void removeNode(Node<K,V> node){
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }
        /** 2.4 获得最后一个节点**/
        public Node getLast(){
            return tail.prev;
        }
    }

    private int cacheSize;
    Map<Integer,Node<Integer,Integer>> map;
    DoubleLinkedList<Integer,Integer> doubleLinkedList;

    public LRUCachDemo02(int cacheSize){
        /**坑位**/
        this.cacheSize = cacheSize;
        /**查找**/
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }
    //获取值map值，链表重排序  删除旧位置 放到新位置
    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }
    //插入map，链表 新值
    public void put(int key,int value){
        if(map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key,node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        }else {
            if(map.size() == cacheSize){
                Node<Integer,Integer> lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            /** 新增 **/
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key,newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    public static void main(String[] args) {
        LRUCachDemo02 lruCachDemo02 = new LRUCachDemo02(3);

        lruCachDemo02.put(1,1);
        lruCachDemo02.put(2,2);
        lruCachDemo02.put(3,3);
        System.out.println(lruCachDemo02.map.keySet());

        lruCachDemo02.put(4,4);
        System.out.println(lruCachDemo02.map.keySet());
    }

}