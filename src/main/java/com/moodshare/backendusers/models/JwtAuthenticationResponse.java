package com.moodshare.backendusers.models;

public class JwtAuthenticationResponse {

    private String token;
    private Long userId;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}



// TODO: ok sin ID
//package com.moodshare.backendusers.models;
//public class JwtAuthenticationResponse {
//
//    private String token;
//
//    public JwtAuthenticationResponse() {
//    }
//
//    public JwtAuthenticationResponse(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//}
