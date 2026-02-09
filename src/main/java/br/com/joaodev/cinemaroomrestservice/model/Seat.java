package br.com.joaodev.cinemaroomrestservice.model;

public class Seat {
    private int row;
    private int column;
    private int price;
    boolean available;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 0;
        this.available = true;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
