package org.mateuszlaski.logon.auth.dto;

public class LoginResponse {
    private final String message;

    public LoginResponse(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
