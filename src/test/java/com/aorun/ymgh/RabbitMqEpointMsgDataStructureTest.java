package com.aorun.ymgh;

import com.aorun.EpointMsgDataStructure;
import com.aorun.ymgh.rabbitmq_direct.SenderEpointMsgDataStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqEpointMsgDataStructureTest {

    @Autowired
    private SenderEpointMsgDataStructure senderEpointMsgDataStructure;

    @Test
    public void sendEpointMsgDataStructure() throws Exception {
        EpointMsgDataStructure epointMsgDataStructure = new EpointMsgDataStructure();

        epointMsgDataStructure.setBizUniqueSignCode("zz");
        epointMsgDataStructure.setEpoint(0);
        epointMsgDataStructure.setEpointConfigCode("TASK_9");
        epointMsgDataStructure.setMsgId(UUID.randomUUID().toString());
        epointMsgDataStructure.setWorkerId(3L);

        senderEpointMsgDataStructure.sendObject(epointMsgDataStructure);
    }

}