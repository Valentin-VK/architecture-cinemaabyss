package com.cinemaabyss.events.service.service;

import com.cinemaabyss.events.service.model.EventMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topicName, int partition, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, partition, key, value);
        kafkaTemplate.send(record).addCallback(
                result -> logger.info("Sent message to {} with key {}", topicName, key),
                ex -> logger.error("Error sending message to {}: {}", topicName, ex)
        );
    }
}
