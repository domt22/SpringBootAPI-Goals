package com.example.springbootapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InitiativeNotFoundException extends RuntimeException {
    public InitiativeNotFoundException(Long id) {
        super("Initiative with id " + id + " not found");
    }
}
