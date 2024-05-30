package com.moodshare.backendusers.security;

import com.moodshare.backendusers.service.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Configuración de seguridad para la aplicación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final IUserService userService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtTokenProvider tokenProvider;

    /**
     * Constructor de SecurityConfiguration.
     *
     * @param userService El servicio de usuarios utilizado para la autenticación.
     * @param unauthorizedHandler El manejador de entradas no autorizadas.
     * @param tokenProvider El proveedor de tokens JWT.
     */
    public SecurityConfiguration(IUserService userService, JwtAuthenticationEntryPoint unauthorizedHandler, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Configurar el AuthenticationManager.
     *
     * @param http El objeto HttpSecurity.
     * @return El AuthenticationManager configurado.
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }

    /**
     * Configurar el SecurityFilterChain.
     *
     * @param http El objeto HttpSecurity.
     * @return El SecurityFilterChain configurado.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Habilita CORS utilizando Customizer
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/api/registro", "/api/login").permitAll()
                                .requestMatchers("/api/users/**").permitAll()
                                .requestMatchers("/api/favs/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configurar el DaoAuthenticationProvider.
     *
     * @param passwordEncoder El codificador de contraseñas.
     * @return El DaoAuthenticationProvider configurado.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configurar el filtro de autenticación JWT.
     *
     * @return El filtro de autenticación JWT.
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(tokenProvider, userService, authenticationManagerBean(null));
    }

    /**
     * Configurar la fuente de configuración de CORS.
     *
     * @return La fuente de configuración de CORS.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}