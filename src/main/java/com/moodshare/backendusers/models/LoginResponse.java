package com.moodshare.backendusers.models;

/**
 * Clase para representar una respuesta de inicio de sesión.
 */
public class LoginResponse {
    private String token; // token JWT para la autenticación del usuario.
    private Long userId; // ID del usuario que ha iniciado sesión.

    /**
     * Constructor con parámetros.
     *
     * @param token  el token JWT para la autenticación del usuario
     * @param userId el ID del usuario que ha iniciado sesión
     */
    public LoginResponse(String token, Long userId) {
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
