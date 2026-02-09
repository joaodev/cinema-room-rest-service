package br.com.joaodev.cinemaroomrestservice.service;

import br.com.joaodev.cinemaroomrestservice.model.Cinema;
import br.com.joaodev.cinemaroomrestservice.model.Seat;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CinemaService {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

    private List<Seat> allSeats;
    private final Map<String, Seat> purchasedSeats = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        allSeats = new ArrayList<>();
        for (int r = 1; r <= ROWS; r++) {
            for (int c = 1; c <= COLUMNS; c++) {
                allSeats.add(new Seat(r, c));
            }
        }
    }

    public Cinema getSeats() {
        List<Seat> availableSeats = allSeats.stream()
                .filter(Seat::isAvailable)
                .collect(Collectors.toList());

        return new Cinema(ROWS, COLUMNS, availableSeats);
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }
}
