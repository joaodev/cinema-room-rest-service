package br.com.joaodev.cinemaroomrestservice.exception;

public class SeatAlreadyPurchasedException extends RuntimeException {
    public SeatAlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
}
