package br.com.joaodev.cinemaroomrestservice.service;

import br.com.joaodev.cinemaroomrestservice.dto.PurchaseRequest;
import br.com.joaodev.cinemaroomrestservice.dto.PurchaseResponse;
import br.com.joaodev.cinemaroomrestservice.exception.SeatAlreadyPurchasedException;
import br.com.joaodev.cinemaroomrestservice.exception.SeatOutOfBoundsException;
import br.com.joaodev.cinemaroomrestservice.model.Cinema;
import br.com.joaodev.cinemaroomrestservice.model.Seat;
import br.com.joaodev.cinemaroomrestservice.model.Ticket;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    public synchronized PurchaseResponse purchase(PurchaseRequest req) {
        validateBounds(req.getRow(), req.getColumn());

        Seat seat = findSeat(req.getRow(), req.getColumn());
        if (seat == null || !seat.isAvailable()) {
            throw new SeatAlreadyPurchasedException();
        }

        seat.setAvailable(false);

        String token = UUID.randomUUID().toString();
        setPurchasedSeats(seat, token);

        Ticket ticket = new Ticket(seat.getRow(), seat.getColumn(), seat.getPrice());
        return new PurchaseResponse(token, ticket);
    }

    private void validateBounds(int row, int column) {
        if (row < 1 || row > ROWS || column < 1 || column > COLUMNS) {
            throw new SeatOutOfBoundsException();
        }
    }

    private Seat findSeat(int row, int column) {
        for (Seat seat : allSeats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat;
            }
        }
        return null;
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }

    public List<Seat> getAllSeats() {
        return allSeats;
    }

    public void  setPurchasedSeats(Seat seat, String token) {
        seat.setAvailable(false);
        purchasedSeats.put(token, seat);
    }

    public Map<String, Seat> getPurchasedSeats(){
        return purchasedSeats;
    }
}
