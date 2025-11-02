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
public class PaymentEventRequest {
    @JsonProperty("payment_id")
    private int paymentId;
    @JsonProperty("user_id")
    private int userId;
    private double amount;
    private String status;
    private String timestamp;
    @JsonProperty("method_type")
    private String methodType;

    public static PaymentEventRequest fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, PaymentEventRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize EventMessage", e);
        }
    }
}
