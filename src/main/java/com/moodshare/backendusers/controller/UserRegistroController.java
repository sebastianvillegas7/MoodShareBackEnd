package com.moodshare.backendusers.controller;

import com.moodshare.backendusers.dto.UserRegistroDTO;
import com.moodshare.backendusers.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registro")
public class UserRegistroController {
    @Autowired
    private IUserService userService;

    @ModelAttribute("user")
    public UserRegistroDTO retornarNuevoUserRegistroDTO() {
        return new UserRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaUser(@ModelAttribute("user") UserRegistroDTO userRegistroDTO) {
        userService.guardar(userRegistroDTO);
        return "redirect:/registro?exito";
    }
}
