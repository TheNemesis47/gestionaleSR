package org.gestionale.gestionalesr.resource;

public class AuthResponse {
    private String token;
    private String email;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public AuthResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public AuthResponse(String token, String email, String userType) {
        this.token = token;
        this.email = email;
        this.userType = userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
