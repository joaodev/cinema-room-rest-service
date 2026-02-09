package br.com.joaodev.cinemaroomrestservice.dto;

public class ReturnRequest {
    private String token;

    public ReturnRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
