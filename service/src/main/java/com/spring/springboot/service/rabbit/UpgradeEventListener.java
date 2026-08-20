package com.spring.springboot.service.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpgradeEventListener {

    @RabbitListener(queues = "upgrade.created.queue")
    public void handleUpgradeCreated(String message) {
        log.info("📨 Received from upgrade.created.queue: {}", message);
    }

    @RabbitListener(queues = "upgrade.updated.queue")
    public void handleUpgradeUpdated(String message) {
        log.info("📨 Received from upgrade.updated.queue: {}", message);
    }

    @RabbitListener(queues = "upgrade.deleted.queue")
    public void handleUpgradeDeleted(String message) {
        log.info("📨 Received from upgrade.deleted.queue: {}", message);
    }
}
