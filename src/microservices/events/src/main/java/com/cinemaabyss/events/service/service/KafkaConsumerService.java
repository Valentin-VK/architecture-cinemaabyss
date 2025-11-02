package com.cinemaabyss.events.service.service;

import com.cinemaabyss.events.service.model.MovieEventRequest;
import com.cinemaabyss.events.service.model.PaymentEventRequest;
import com.cinemaabyss.events.service.model.UserEventRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${kafka.topic.movie-events}")
    public void receiveMovieEvents(String message) {
        MovieEventRequest event = MovieEventRequest.fromJson(message); // Предположим метод парсинга JSON
        logger.info("Received event {}", event);
    }

    @KafkaListener(topics = "${kafka.topic.user-events}")
    public void receiveUserEvents(String message) {
        UserEventRequest event = UserEventRequest.fromJson(message); // Предположим метод парсинга JSON
        logger.info("Received event {}", event);
    }

    @KafkaListener(topics = "${kafka.topic.payment-events}")
    public void receivePaymentEvents(String message) {
        PaymentEventRequest event = PaymentEventRequest.fromJson(message); // Предположим метод парсинга JSON
        logger.info("Received event {}", event);
    }
}
