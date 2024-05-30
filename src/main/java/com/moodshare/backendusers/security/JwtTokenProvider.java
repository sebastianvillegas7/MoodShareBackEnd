package com.moodshare.backendusers.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Proveedor de tokens JWT para la autenticación y autorización de usuarios.
 */
@Component
public class JwtTokenProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;

    /**
     * Generar un token JWT para la autenticación del usuario.
     *
     * @param authentication La autenticación del usuario.
     * @return El token JWT generado.
     */
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    /**
     * Obtener el nombre de usuario del token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario extraído del token.
     */
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Validar un token JWT.
     *
     * @param authToken El token JWT.
     * @return True si el token es válido, False en caso contrario.
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}