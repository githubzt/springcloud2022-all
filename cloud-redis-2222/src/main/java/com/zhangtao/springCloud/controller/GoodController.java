package com.zhangtao.springCloud.controller;

import com.zhangtao.springCloud.utils.RedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. @ClassDescription:  分布式锁案例
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月01日 17:57
 */
@RestController
public class GoodController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    private final Lock lock = new ReentrantLock();
    //定义一个锁
    public static final String BANK_REDIS_LOCK = "GoodLock";

    /**Redisson 实现锁**/
    @Autowired
    private Redisson redisson;

    @GetMapping("/buy_goods/{key}")  //key 为 goods:001
    public String buy_Goods(@PathVariable("key") String key) throws Exception {
        String good_value = null;
        /**
         * 1、synchronized\lock 单机锁，无法解决分布式部署程序的超卖现象   2.0版本  overBayRedis2(key)；
         * 2、中途出现异常，没法处理，必须要加finally，如果宕机了key删除不了  3.0版本 redis分布式锁 setnx
         * 3、如果宕机了key删除不了，需要设置主动过期时间处理   3.0版本 redis分布式锁 setnx
         * 4、张冠李戴 如果A业务超过设置过期时间，redis主动释放锁，线程B接着获取了，接着A完成，删掉B的锁… 注意：只能删除自己锁  3.0版本 redis分布式锁 setnx
         * 5、到了缓存时间，超时怎么处理   缓存续期
         * 6、redis异步复制造成的锁丢失，例如：主节点没来的及把刚刚set进来这条数据给从节点，就挂了 从机上来没这个数。
         *   另：zookeeper cp(强一致) 也可以实现分布式锁，不会有异步复制导致丢失的问题， 但是不是高可用的，一般不会采用
         *  综上 推出了 Redisson     4.0版本 overBayRedis4(key); redisson实现
         *
         */
         //good_value = overBayRedis2(key);
         //good_value = overBayRedis3(key);
          good_value = overBayRedis4(key);
        return good_value ;
    }
    /** Redisson 实现分布式锁  **/
    public String overBayRedis4(String key) throws Exception {
        RLock redissonLock = redisson.getLock(BANK_REDIS_LOCK);
        redissonLock.lock();
        try{
            String result = stringRedisTemplate.opsForValue().get(key);
            int goodsNum = result == null ? 0 : Integer.valueOf(result);
            if(goodsNum>0){
                //库存
                int realNum = goodsNum - 1;
                stringRedisTemplate.opsForValue().set(key,String.valueOf(realNum));
                System.out.println("成功买到商品，库存还剩下: " + realNum + " 件， 服务提供端口: " + serverPort);
                return "成功买到商品，库存还剩下: " + realNum + " 件， 服务提供端口: " + serverPort;
            }else {
                System.out.println("商品已经售完，欢迎下次光临。服务提供端口: " + serverPort);
            }
            return "商品已经售完，欢迎下次光临。服务提供端口: " + serverPort;
        }finally {
            redissonLock.unlock();
        }
    }

    public String overBayRedis3(String key) throws Exception {
        //方式一  手写锁  setIfAbsent和NX一样 相当于 setNX 缺席添加
        String userlockid = UUID.randomUUID().toString() + Thread.currentThread().getName();
        try{
            //方式一 手写锁  设置过期时间 以防宕机key删不掉.
            //Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(BANK_REDIS_LOCK, userlockid);
            //这种写法不对:  设置key + 过期时间分开了，必须要合成一行具备原子性
            //if(flag){stringRedisTemplate.expire(BANK_REDIS_LOCK,10L, TimeUnit.SECONDS);}
            //方式二 手写锁  设置过期时间 以防宕机key删不掉.
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(BANK_REDIS_LOCK, userlockid, 10L, TimeUnit.SECONDS);

            if (!flag) {
                return "抢锁失败，请重新！";
            }
            String result = stringRedisTemplate.opsForValue().get(key);
            int goodsNum = result == null ? 0 : Integer.valueOf(result);
            if(goodsNum>0){
                //库存
                int realNum = goodsNum - 1;
                stringRedisTemplate.opsForValue().set(key,String.valueOf(realNum));
                System.out.println("成功买到商品，库存还剩下: " + realNum + " 件， 服务提供端口: " + serverPort);
                return "成功买到商品，库存还剩下: " + realNum + " 件， 服务提供端口: " + serverPort;
            }else {
                System.out.println("商品已经售完，欢迎下次光临。服务提供端口: " + serverPort);
            }
            return "商品已经售完，欢迎下次光临。服务提供端口: " + serverPort;
        }finally {
            //方式一  只能删除自己的锁，不能删除别人的锁  错误写法：这个判断不是原子性的
           /* if(stringRedisTemplate.opsForValue().get(BANK_REDIS_LOCK).equals(userlockid)){
                //买完产品 删除锁
                stringRedisTemplate.delete(BANK_REDIS_LOCK);
            }*/
            //方式二  调用一个lua脚本 来解决问题  工作中常用
            Jedis jedis = RedisUtils.getJedis();
            String script = "if redis.call('get',KEY[1]) == ARGV[1] " +
                "then " +
                "return redis.call('del',KEYS[1]) " +
                "else " +
                " return 0" +
                "end";
            try {
                Object eval = jedis.eval(script, Collections.singletonList(BANK_REDIS_LOCK), Collections.singletonList(userlockid));
                if("1".equals(eval)){
                    System.out.println("----del redis lock ok");
                }else {
                    System.out.println("-----del redis lock error");
                }
            }finally {
                if(null != jedis){
                    jedis.close();
                }
            }
            //方式三  用事务来处理
            /*while (true){
                stringRedisTemplate.watch(BANK_REDIS_LOCK);
                if(stringRedisTemplate.opsForValue().get(BANK_REDIS_LOCK).equals(userlockid)){
                    stringRedisTemplate.setEnableTransactionSupport(true);
                    stringRedisTemplate.multi();
                    stringRedisTemplate.delete(BANK_REDIS_LOCK);
                    List<Object> list = stringRedisTemplate.exec();
                    if(list==null){
                        continue;
                    }
                }
                stringRedisTemplate.delete(BANK_REDIS_LOCK);
                break;
            }*/


        }
    }

    public String overBayRedis2(String key) {
        //不见不散 一直等待    单机锁，无法解决分布式部署程序的超卖现象
        synchronized (this){
            String result = stringRedisTemplate.opsForValue().get(key);
            int goodsNum = result == null ? 0 : Integer.valueOf(result);
            if(goodsNum>0){
                //库存
                int realNum = goodsNum - 1;
                stringRedisTemplate.opsForValue().set(key,String.valueOf(realNum));
                System.out.println("成功买到商品，库存还剩下: " + realNum + " 件， 服务提供端口: " + serverPort);
                return "成功买到商品，库存还剩下: " + realNum + " 件， 服务提供端口: " + serverPort;
            }else {
                System.out.println("商品已经售完，欢迎下次光临。服务提供端口: " + serverPort);
            }
            return "商品已经售完，欢迎下次光临。服务提供端口: " + serverPort;
        }

        //单机版 等待时间太长，放弃等待  规定一个时间内，拿不到锁就放弃
        //if(lock.tryLock()){}else {}
        //单机版  规定时间内，拿不到锁就放弃    这个选择需要根据业务来定
        //if(lock.tryLock(3L, TimeUnit.SECONDS)){}else {}
    }


}