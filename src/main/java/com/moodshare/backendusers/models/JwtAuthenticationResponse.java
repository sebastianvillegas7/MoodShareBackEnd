package com.moodshare.backendusers.models;

/**
 * Clase para representar la respuesta de autenticación JWT.
 */
public class JwtAuthenticationResponse {
    private String token; // Token JWT generado tras la autenticación.
    private Long userId; // ID del usuario autenticado.


    public JwtAuthenticationResponse() { }

    /**
     * Constructor con parámetros.
     *
     * @param token el token JWT
     * @param userId el ID del usuario autenticado
     */
    public JwtAuthenticationResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    // Getters y Setters
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
