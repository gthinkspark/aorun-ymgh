package com.aorun.ymgh.rabbitmq_direct;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String epointMsgDataStructureQueue = "epointMsgDataStructureQueue";

    //积分对象队列
    @Bean
    public Queue epointMsgDataStructureQueue() {
        return new Queue(RabbitConfig.epointMsgDataStructureQueue);
    }


}