package com.spring.springboot.service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue unitCreatedQueue() {
        return new Queue("unit.created.queue", true);
    }

    @Bean
    public Queue unitUpdatedQueue() {
        return new Queue("unit.updated.queue", true);
    }

    @Bean
    public Queue unitDeletedQueue() {
        return new Queue("unit.deleted.queue", true);
    }

    @Bean
    public TopicExchange unitExchange() {
        return new TopicExchange("unit.exchange");
    }

    @Bean
    public Binding unitCreatedBinding() {
        return BindingBuilder.bind(unitCreatedQueue())
                .to(unitExchange())
                .with("unit.created");
    }

    @Bean
    public Binding unitUpdatedBinding() {
        return BindingBuilder.bind(unitUpdatedQueue())
                .to(unitExchange())
                .with("unit.updated");
    }

    @Bean
    public Binding unitDeletedBinding() {
        return BindingBuilder.bind(unitDeletedQueue())
                .to(unitExchange())
                .with("unit.deleted");
    }
}