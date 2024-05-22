package com.moodshare.backendusers.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // La vista del formulario de inicio de sesión
    }

    @RequestMapping("/login-success")
    public String loginSuccess() {
        // Obtener el nombre de usuario del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Redirigir al usuario a la página de inicio
        return "redirect:/index";
    }
}
