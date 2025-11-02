package com.cinemaabyss.events.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/api/events/health")
    public ResponseEntity<?> healthCheck() {
        // Возвращаем объект с телом {"status": true}, код ответа - 200 OK
        return ResponseEntity.ok().body(new HealthStatus(true));
    }

    private static class HealthStatus {
        private final boolean status;

        public HealthStatus(boolean status) {
            this.status = status;
        }

        public boolean getStatus() {
            return status;
        }
    }
}
