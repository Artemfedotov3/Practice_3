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

    @Bean
    public Queue equipmentCreatedQueue(){
        return new Queue("equipment.created.queue");
    }

    @Bean
    public Queue equipmentUpdatedQueue(){
        return new Queue("equipment.updated.queue");
    }

    @Bean
    public Queue equipmentDeletedQueue(){
        return new Queue("equipment.deleted.queue");
    }

    @Bean
    public TopicExchange equipmentExchange(){
        return new TopicExchange("equipment.exchange");
    }

    @Bean
    public Binding equipmentCreatedBinding(){
        return BindingBuilder.bind(equipmentCreatedQueue())
                .to(equipmentExchange())
                .with("equipment.created");
    }

    @Bean
    public Binding equipmentUpdatedBinding(){
        return BindingBuilder.bind(equipmentUpdatedQueue())
                .to(equipmentExchange())
                .with("equipment.updated");
    }

    @Bean
    public Binding equpmentDeletedBinding() {
        return BindingBuilder.bind(equipmentDeletedQueue())
                .to(equipmentExchange())
                .with("equipment.deleted");
    }

    @Bean
    public Queue armourCreatedQueue(){
        return new Queue("armour.created.queue", true);
    }

    @Bean
    public Queue armourUpdatedQueue(){
        return new Queue("armour.updated.queue", true);
    }

    @Bean
    public Queue armourDeletedQueue(){
        return new Queue("armour.deleted.queue", true);
    }

    @Bean
    public TopicExchange armourExchange(){
        return new TopicExchange("armour.exchange");
    }

    @Bean
    public Binding armourCreatedBinding(){
        return BindingBuilder.bind(armourCreatedQueue()).to(armourExchange()).with("armour.created");
    }

    @Bean
    public Binding armourUpdatedBinding(){
        return BindingBuilder.bind(armourUpdatedQueue()).to(armourExchange()).with("armour.updated");
    }

    @Bean
    public Binding armourDeletedBinding(){
        return BindingBuilder.bind(armourDeletedQueue()).to(armourExchange()).with("armour.deleted");
    }

    @Bean
    public Queue exoticBeastCreatedQueue(){
        return new Queue("exotic.beast.created.queue");
    }

    @Bean
    public Queue exoticBeastUpdatedQueue(){
        return new Queue("exotic.beast.updated.queue");
    }

    @Bean
    public Queue exoticBeastDeletedQueue(){
        return new Queue("exotic.beast.deleted.queue");
    }

    @Bean
    public TopicExchange exoticBeastExchange(){
        return new TopicExchange("exotic.beast.exchange");
    }

    @Bean
    public Binding exoticBeastCreatedBinding(){
        return BindingBuilder.bind(exoticBeastCreatedQueue())
                .to(exoticBeastExchange()).with("exotic.beast.created");
    }

    @Bean
    public Binding exoticBeastUpdatedBinding(){
        return BindingBuilder.bind(exoticBeastUpdatedQueue())
                .to(exoticBeastExchange()).with("exotic.beast.updated");
    }

    @Bean
    public Binding exoticBeastDeletedBinding(){
        return BindingBuilder.bind(exoticBeastDeletedQueue())
                .to(exoticBeastExchange()).with("exotic.beast.deleted");
    }

    @Bean
    public Queue upgradeCreatedQueue(){
        return new Queue("upgrade.created.queue");
    }

    @Bean
    public Queue upgradeUpdatedQueue(){
        return new Queue("upgrade.updated.queue");
    }

    @Bean
    public Queue upgradeDeletedQueue(){
        return new Queue("upgrade.deleted.queue");
    }

    @Bean
    public TopicExchange upgradeExchange(){
        return new TopicExchange("upgrade.exchange");
    }

    @Bean
    public Binding upgradeCreatedBinding(){
        return BindingBuilder.bind(upgradeCreatedQueue()).to(upgradeExchange()).with("upgrade.created");
    }

    @Bean
    public Binding upgradeUpdatedBinding(){
        return BindingBuilder.bind(upgradeUpdatedQueue()).to(upgradeExchange()).with("upgrade.updated");
    }

    @Bean
    public Binding upgradeDeletedBinding(){
        return BindingBuilder.bind(upgradeDeletedQueue()).to(upgradeExchange()).with("upgrade.deleted");
    }
}