package com.example.springbootapi.exception;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GoalNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGoalNotFoundException(GoalNotFoundException e) {
        return getMapResponseEntity(e.getMessage());
    }

    @ExceptionHandler(InitiativeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleInitiativeNotFoundException(InitiativeNotFoundException e) {
        return getMapResponseEntity(e.getMessage());
    }

    @NonNull
    private ResponseEntity<Map<String, Object>> getMapResponseEntity(String message) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", message);
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }
}
