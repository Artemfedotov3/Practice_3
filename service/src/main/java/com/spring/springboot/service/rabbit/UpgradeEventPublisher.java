package com.spring.springboot.service.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpgradeEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "upgrade.exchange";
    private static final String ROUTING_KEY_CREATED = "upgrade.created";
    private static final String ROUTING_KEY_UPDATED = "upgrade.updated";
    private static final String ROUTING_KEY_DELETED = "upgrade.deleted";

    public void publishUpgradeCreated(Long upgradeId, String upgradeName) {
        String message = String.format("Upgrade created: id=%d, name=%s", upgradeId, upgradeName);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_CREATED, message);
        log.info("Event published: {}", message);
    }

    public void publishUpgradeUpdated(Long upgradeId, String upgradeName) {
        String message = String.format("Upgrade updated: id=%d, name=%s", upgradeId, upgradeName);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_UPDATED, message);
        log.info("Event published: {}", message);
    }

    public void publishUpgradeDeleted(Long upgradeId) {
        String message = String.format("Upgrade deleted: id=%d", upgradeId);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_DELETED, message);
        log.info("Event published: {}", message);
    }
}
