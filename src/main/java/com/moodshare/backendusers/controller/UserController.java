package com.moodshare.backendusers.controller;

import com.moodshare.backendusers.models.User;
import com.moodshare.backendusers.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    // TODO: METODOS PARA DEVOLVER PLANTILLAS
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login"; // Retorna la vista login.html
//    }
//
//    @GetMapping("/registro")
//    public String showRegisterForm(Model model) {
//        model.addAttribute("user", new User());
//        return "registro"; // Retorna la vista registro.html
//    }
//
//    @PostMapping("/api/users/registro")
//    public String createUser(@ModelAttribute User user) {
//        userService.save(user);
//        return "redirect:/login"; // Redirigir al login después del registro exitoso
//    }
    // TODO: METODOS PARA DEVOLVER PLANTILLAS

    @PostMapping("/api/users/registro")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
