package com.cinemaabyss.events.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;

@Data
@AllArgsConstructor(onConstructor_ = {@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)})
public class EventMessage {
    private EventType type;
    private Long timestamp;
    private String details;


    // Метод для преобразования JSON в объект EventMessage
    public static EventMessage fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, EventMessage.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize EventMessage", e);
        }
    }

    // Метод для сериализации объекта в JSON
    public String toJson() throws IOException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
