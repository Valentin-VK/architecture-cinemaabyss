package com.cinemaabyss.events.service.controller;

import com.cinemaabyss.events.service.model.MovieEventRequest;
import com.cinemaabyss.events.service.model.PaymentEventRequest;
import com.cinemaabyss.events.service.model.UserEventRequest;
import com.cinemaabyss.events.service.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EventController {

    @Value("${kafka.topic.movie-events}")
    private String topicName;

    private final KafkaProducerService kafkaProducerService;

    public EventController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/api/events/movie")
    public ResponseEntity<Map<String, Object>> handleMovieEvent(@RequestBody MovieEventRequest request) {
        try {
            // Формируем сообщение для Kafka
            String message = createKafkaMessage(request);

            // Отправляем сообщение в Kafka-тему "movie-events" с указанием партиции и ключа
            kafkaProducerService.send(topicName, 1, Integer.toString(request.getUserId()), message);

            return new ResponseEntity<>(Map.of("message", "Сообщение успешно отправлено в Kafka.","status", "success"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage(),"status", "error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/events/user")
    public ResponseEntity<Map<String, Object>> handleUserEvent(@RequestBody UserEventRequest event) {
        try {
            // Преобразование объекта в JSON-строку
            String jsonPayload = createKafkaMessage(event);

            // Отправляем сообщение в Kafka
            kafkaProducerService.send("user-events", 1, Integer.toString(event.getUserId()), jsonPayload);

            return new ResponseEntity<>(Map.of("message", "Сообщение успешно отправлено в Kafka.","status", "success"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage(),"status", "error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/events/payment")
    public ResponseEntity<Map<String, Object>> handlePaymentEvent(@RequestBody PaymentEventRequest event) {
        try {
            // Конвертируем объект в JSON-строку
            String jsonPayload = createKafkaMessage(event);

            // Отправляем сообщение в Kafka
            kafkaProducerService.send("payment-events", 1, Integer.toString(event.getPaymentId()), jsonPayload);

            return new ResponseEntity<>(Map.of("message", "Сообщение успешно отправлено в Kafka.","status", "success"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage(),"status", "error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Формирование сообщения для передачи в Kafka
     */
    private String createKafkaMessage(MovieEventRequest request) {
        return "{\"movie_id\":\"" + request.getMovieId()
                + "\", \"title\":\"" + request.getTitle()
                + "\", \"action\":\"" + request.getAction()
                + "\", \"user_id\":\"" + request.getUserId() + "\"}";
    }

    private String createKafkaMessage(UserEventRequest request) {
        return "{"
                + "\"user_id\":\"" + request.getUserId()
                + "\",\"username\":\"" + request.getUsername()
                + "\",\"action\":\"" + request.getAction()
                + "\",\"timestamp\":\"" + request.getTimestamp() + "\""
                + "}";
    }

    private String createKafkaMessage(PaymentEventRequest request) {
        return "{"
                + "\"payment_id\":\"" + request.getPaymentId()
                + "\",\"user_id\":\"" + request.getUserId()
                + "\",\"amount\":\"" + request.getAmount()
                + "\",\"status\":\"" + request.getStatus()
                + "\",\"timestamp\":\"" + request.getTimestamp()
                + "\",\"method_type\":\"" + request.getMethodType() + "\""
                + "}";
    }
}
