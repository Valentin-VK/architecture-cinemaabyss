package com.cinemaabyss.events.service.controller;

import com.cinemaabyss.events.service.model.EventMessage;
import com.cinemaabyss.events.service.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final KafkaProducerService kafkaProducerService;

    public EventController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send-event")
    public void sendEvent(@RequestBody EventMessage event) {
        kafkaProducerService.sendEvent(event);
    }
}
