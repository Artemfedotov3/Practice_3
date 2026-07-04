package com.spring.springboot.service.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ArmourEventListener {

    @RabbitListener(queues = "armour.created.queue")
    public void handleArmourCreated(String message){
        log.info("Received from armour.created.queue", message);
    }

    @RabbitListener(queues = "armour.updated.queue")
    public void handleArmourUpdated(String message){
        log.info("Received from armour.updated.queue", message);
    }

    @RabbitListener(queues = "armour.deleted.queue")
    public void handleArmourDeleted(String message){
        log.info("Received from armour.deleted.queue", message);
    }
}
