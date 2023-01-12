package com.zhangtao.springcloud.service.impl;

import com.zhangtao.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月01日 15:40
 */
@EnableBinding(Source.class)  //定义消息生产者推送管道 output
@Slf4j
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {

        String serilno = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serilno).build());
        log.info("*********rabbitMQ serilno: "+serilno);
        return null;
    }
}