package com.spring.springboot.service.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ArmourEventPublisher {

    private final RabbitTemplate armourRabbitTemplate;

    private static final String EXCHANGE = "armour.exchange";
    private static final String ROUTING_KEY_CREATED = "armour.created";
    private static final String ROUTING_KEY_UPDATED = "armour.updated";
    private static final String ROUTING_KEY_DELETED = "armour.deleted";

    public void publishArmourCreated(Long armourId, String name, String type){
        String message = String.format("Armour created: id=%d, name=%s, type=%s", armourId, name, type);
        armourRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_CREATED, message);
        log.info("Event published: {}", message);
    }

    public void publishArmourUpdated(Long armourId, String name, String type){
        String message = String.format("Armour updated: id=%d, name=%s, type=%s", armourId, name, type);
        armourRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_UPDATED, message);
        log.info("Event published: {}", message);
    }

    public void publishArmourDeleted(Long armourId){
        String message = String.format("Armour deleted: id=%d", armourId);
        armourRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_DELETED, message);
        log.info("Event published: {}", message);
    }
}
