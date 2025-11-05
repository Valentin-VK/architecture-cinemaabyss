package com.cinemaabyss.events.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventRequest {
    @JsonProperty("user_id")
    private int userId;
    private String username;
    private String action;
    private String timestamp;

    public static UserEventRequest fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, UserEventRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize EventMessage", e);
        }
    }
}
