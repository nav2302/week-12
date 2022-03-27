package com.greatlearning.week12assignment.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class ConsumerService {

    @KafkaListener(topics = "userToAdminTopic", groupId = "admin_id")
    public void consume(String message) {
        log.info(String.format("$$$$ => Consumed message sent from user : %s", message));
    }
}