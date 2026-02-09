package br.com.joaodev.cinemaroomrestservice.dto;

public class PurchaseRequest {
    private int row;
    private int column;

    public PurchaseRequest(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
