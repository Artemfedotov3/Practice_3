package com.spring.springboot.service.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EquipmentEventPublisher {

    private final RabbitTemplate equipmentRabbitTemplate;

    private static final String EXCHANGE = "equipment.exchange";
    private static final String ROUTING_KEY_CREATED = "equipment.created";
    private static final String ROUTING_KEY_UPDATED = "equipment.updated";
    private static final String ROUTING_KEY_DELETED = "equipment.deleted";

    public void publishEquipmentCreated(Long equipmentId, String name, String type){
        String message = String.format("Equipment created: id=%d, name=%s, type=%s", equipmentId, name, type);
        equipmentRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_CREATED, message);
        log.info("Event published: {}", message);
    }

    public void publishEquipmentUpdated(Long equipmentId, String name, String type){
        String message = String.format("Equipment updated: id=%d, name=%s, type=%s", equipmentId, name, type);
        equipmentRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_UPDATED, message);
        log.info("Event published: {}", message);
    }

    public void publishEquipmentDeleted(Long equipmentId){
        String message = String.format("Equipment deleted: id=%d", equipmentId);
        equipmentRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_DELETED, message);
        log.info("Event published: {}", message);
    }
}
