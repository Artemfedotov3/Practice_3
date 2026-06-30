package com.spring.springboot.service.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnitEventListener {

    @RabbitListener(queues = "unit.created.queue")
    public void handleUnitCreated(String message) {
        log.info("📨 Received from unit.created.queue: {}", message);
    }

    @RabbitListener(queues = "unit.updated.queue")
    public void handleUnitUpdated(String message) {
        log.info("📨 Received from unit.updated.queue: {}", message);
    }

    @RabbitListener(queues = "unit.deleted.queue")
    public void handleUnitDeleted(String message) {
        log.info("📨 Received from unit.deleted.queue: {}", message);
    }
}