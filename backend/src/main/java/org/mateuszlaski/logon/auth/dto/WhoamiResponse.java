package org.mateuszlaski.logon.auth.dto;

public class WhoamiResponse {
    String username;
    String email;

    public WhoamiResponse() {
        username = "";
        email = "";
    }

    public WhoamiResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
