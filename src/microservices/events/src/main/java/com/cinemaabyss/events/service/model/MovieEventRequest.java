package com.cinemaabyss.events.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

@Data
public class MovieEventRequest {
    @JsonProperty("movie_id")
    private int movieId;
    private String title;
    private String action;
    @JsonProperty("user_id")
    private int userId;

    public static MovieEventRequest fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, MovieEventRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize EventMessage", e);
        }
    }

    // Метод для сериализации объекта в JSON
    public String toJson() throws IOException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
