package com.cinemaabyss.events.service.service;

import com.cinemaabyss.events.service.model.EventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "events-topic")
    public void receive(String message) {
        EventMessage event = EventMessage.fromJson(message); // Предположим метод парсинга JSON
        logger.info("Received event {}", event);
    }
}
