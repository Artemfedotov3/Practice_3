package com.spring.springboot.service.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UnitEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "unit.exchange";
    private static final String ROUTING_KEY_CREATED = "unit.created";
    private static final String ROUTING_KEY_UPDATED = "unit.updated";
    private static final String ROUTING_KEY_DELETED = "unit.deleted";

    public void publishUnitCreated(Long unitId, String name, String type) {
        String message = String.format("Unit created: id=%d, name=%s, type=%s", unitId, name, type);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_CREATED, message);
        log.info("Event published: {}", message);
    }

    public void publishUnitUpdated(Long unitId, String name, String type) {
        String message = String.format("Unit updated: id=%d, name=%s, type=%s", unitId, name, type);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_UPDATED, message);
        log.info("Event published: {}", message);
    }

    public void publishUnitDeleted(Long unitId) {
        String message = String.format("Unit deleted: id=%d", unitId);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_DELETED, message);
        log.info("Event published: {}", message);
    }
}