package com.spring.springboot.service.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExoticBeastEventListener {

    @RabbitListener(queues = "exotic.beast.created.queue")
    public void handleExoticBeastCreated(String message){
        log.info("📨 Received from exotic.beast.created.queue: {}", message);
    }

    @RabbitListener(queues = "exotic.beast.updated.queue")
    public void handleExoticBeastUpdated(String message){
        log.info("📨 Received from exotic.beast.updated.queue: {}", message);
    }

    @RabbitListener(queues = "exotic.beast.deleted.queue")
    public void handleExoticBeastDeleted(String message){
        log.info("📨 Received from exotic.beast.deleted.queue: {}", message);
    }
}
