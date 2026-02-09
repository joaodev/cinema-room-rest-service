package br.com.joaodev.cinemaroomrestservice.controller;

import br.com.joaodev.cinemaroomrestservice.dto.*;
import br.com.joaodev.cinemaroomrestservice.model.Cinema;
import br.com.joaodev.cinemaroomrestservice.model.Seat;
import br.com.joaodev.cinemaroomrestservice.model.Ticket;
import br.com.joaodev.cinemaroomrestservice.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CinemaController {
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinemaService.getSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> purchase(@RequestBody PurchaseRequest req) {
        return ResponseEntity.ok(cinemaService.purchase(req));
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnResponse> returnTicket(@RequestBody ReturnRequest req) {
        return ResponseEntity.ok(cinemaService.returnTicket(req));
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        return ResponseEntity.ok(cinemaService.getStats());
    }
}
