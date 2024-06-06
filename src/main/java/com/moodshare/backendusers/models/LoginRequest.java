package com.moodshare.backendusers.models;

/**
 * Clase para representar una solicitud de inicio de sesión.
 */
public class LoginRequest {
    private String email; // Correo electrónico del usuario.
    private String password; // Contraseña del usuario.

    public LoginRequest() { }

    /**
     * Constructor con parámetros.
     *
     * @param email el correo electrónico del usuario
     * @param password la contraseña del usuario
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
