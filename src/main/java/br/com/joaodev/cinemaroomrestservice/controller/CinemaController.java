package br.com.joaodev.cinemaroomrestservice.controller;

import br.com.joaodev.cinemaroomrestservice.model.Cinema;
import br.com.joaodev.cinemaroomrestservice.model.Seat;
import br.com.joaodev.cinemaroomrestservice.service.CinemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
}
