package br.com.joaodev.cinemaroomrestservice.dto;

import br.com.joaodev.cinemaroomrestservice.model.Ticket;

public class ReturnResponse {
    private Ticket ticket;

    public ReturnResponse(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
