package com.spring.springboot.service.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EquipmentEventListener {

    @RabbitListener(queues = "equipment.created.queue")
    public void handleEquipmentCreated(String message) {
        log.info("📨 Received from quipment.created.queue: {}", message);
    }

    @RabbitListener(queues = "equipment.updated.queue")
    public void handleEquipmentUpdated(String message) {
        log.info("📨 Received from quipment.updated.queue: {}", message);
    }

    @RabbitListener(queues = "equipment.deleted.queue")
    public void handleEquipmentDeleted(String message) {
        log.info("📨 Received from quipment.deleted.queue: {}", message);
    }
}
