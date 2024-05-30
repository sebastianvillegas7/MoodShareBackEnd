package com.moodshare.backendusers.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Punto de entrada de autenticación JWT que maneja intentos de acceso no autorizados.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Manejar intentos de acceso no autorizados enviando un error 401 (Unauthorized).
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @param authException La excepción de autenticación.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
