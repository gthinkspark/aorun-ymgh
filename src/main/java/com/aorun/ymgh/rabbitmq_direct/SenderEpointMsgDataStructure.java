package com.aorun.ymgh.rabbitmq_direct;

import com.aorun.EpointMsgDataStructure;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderEpointMsgDataStructure {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    //发送者
    public void sendObject(EpointMsgDataStructure epointMsgDataStructure) {

        System.out.println("Sender object: " + epointMsgDataStructure.toString());
        this.rabbitTemplate.convertAndSend(RabbitConfig.epointMsgDataStructureQueue, epointMsgDataStructure);
    }

}