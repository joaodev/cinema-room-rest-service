package br.com.joaodev.cinemaroomrestservice.controller;

import br.com.joaodev.cinemaroomrestservice.exception.SeatAlreadyPurchasedException;
import br.com.joaodev.cinemaroomrestservice.exception.SeatOutOfBoundsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(SeatOutOfBoundsException.class)
    public ResponseEntity<Map<String, String>> handleOutOfBounds(SeatOutOfBoundsException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(SeatAlreadyPurchasedException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyPurchased(SeatAlreadyPurchasedException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
}
