package com.moodshare.backendusers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index"; // Nombre de la plantilla Thymeleaf correspondiente
    }
}
