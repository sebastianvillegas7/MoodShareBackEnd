package com.moodshare.backendusers.controller;

import com.moodshare.backendusers.models.LoginRequest;
import com.moodshare.backendusers.models.LoginResponse;
import com.moodshare.backendusers.models.User;
import com.moodshare.backendusers.security.JwtTokenProvider;
import com.moodshare.backendusers.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, IUserService userService, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        User user = userService.getUserByEmail(loginRequest.getEmail());
        Long userId = user.getId();

        return ResponseEntity.ok(new LoginResponse(jwt, userId));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Verificar si el usuario ya existe
        User userExiste = userService.getUserByEmail(user.getEmail());
        if (userExiste != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El usuario '"+ user.getEmail() +"' ya existe");
        }

        try {
            // Intentar guardar el nuevo usuario
            User savedUser = userService.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Si se produce una excepción de violación de integridad, devolver una respuesta de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        user.setPassword("PROTECTED");
        user.setId(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


// TODO: ok sin ID
//package com.moodshare.backendusers.controller;
//
//import com.moodshare.backendusers.models.LoginRequest;
//import com.moodshare.backendusers.models.LoginResponse;
//import com.moodshare.backendusers.models.User;
//import com.moodshare.backendusers.security.JwtTokenProvider;
//import com.moodshare.backendusers.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class UserController {
//
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @Autowired
//    public UserController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.generateToken(authentication);
//        return ResponseEntity.ok(new LoginResponse(jwt));
//    }
//
//    @PostMapping("/registro")
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        // Verificar si el usuario ya existe
//        User userExiste = userService.getUserByEmail(user.getEmail());
//        if (userExiste != null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body("El usuario '"+ user.getEmail() +"' ya existe");
//        }
//
//        try {
//            // Intentar guardar el nuevo usuario
//            User savedUser = userService.save(user);
//            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//        } catch (DataIntegrityViolationException e) {
//            // Si se produce una excepción de violación de integridad, devolver una respuesta de error
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error al registrar el usuario: " + e.getMessage());
//        }
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userService.getUserById(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//        User user = userService.updateUser(id, updatedUser);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}

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