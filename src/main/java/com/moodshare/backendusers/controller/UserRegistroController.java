package com.moodshare.backendusers.controller;

import com.moodshare.backendusers.dto.UserRegistroDTO;
import com.moodshare.backendusers.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class UserRegistroController {

    @Autowired
    private IUserService userService;

    @GetMapping // Manejar solicitudes GET en /registro
    public String mostrarFormularioRegistro() {
        return "registro"; // Devuelve el nombre de la vista del formulario de registro
    }

    @PostMapping // Manejar solicitudes POST en /registro
    public String registrarCuenta(@ModelAttribute("user") UserRegistroDTO registroDTO) {
        userService.guardar(registroDTO);
        return "redirect:/registro?exito";
    }

    @ModelAttribute("user")
    public UserRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UserRegistroDTO();
    }
}
