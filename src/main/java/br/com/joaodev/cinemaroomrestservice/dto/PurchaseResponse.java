package br.com.joaodev.cinemaroomrestservice.dto;

import br.com.joaodev.cinemaroomrestservice.model.Ticket;

public class PurchaseResponse {
    private String token;
    private Ticket ticket;

    public PurchaseResponse(String token, Ticket ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
