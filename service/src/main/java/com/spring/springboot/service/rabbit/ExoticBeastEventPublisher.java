package com.spring.springboot.service.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExoticBeastEventPublisher {

    private final RabbitTemplate exoticBeastRabbitTemplate;

    private static final String EXCHANGE = "exotic.beast.exchange";
    private static final String ROUTING_KEY_CREATED = "exotic.beast.created";
    private static final String ROUTING_KEY_UPDATED = "exotic.beast.updated";
    private static final String ROUTING_KEY_DELETED = "exotic.beast.deleted";

    public void publishExoticBeastCreated(Long exoticBeastId, String name){
        String message = String.format("Exotic beast created: id=%d, name=%s", exoticBeastId, name);
        exoticBeastRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_CREATED, message);
        log.info("Event published: {}", message);
    }

    public void publishExoticBeastUpdated(Long exoticBestId, String name){
        String message = String.format("Exotic beast updated: id=%d, name=%s", exoticBestId, name);
        exoticBeastRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_UPDATED, message);
        log.info("Event published: {}", message);
    }

    public void publishExoticBeastDeleted(Long exoticBeastId){
        String message = String.format("Exotic beast deleted: id=%d", exoticBeastId);
        exoticBeastRabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_DELETED, message);
        log.info("Event published: {}", message);
    }
}
