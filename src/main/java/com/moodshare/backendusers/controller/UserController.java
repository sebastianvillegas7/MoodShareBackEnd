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

/**
 * Controlador para gestionar operaciones relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final JwtTokenProvider tokenProvider;

    /**
     * Constructor del UserController.
     *
     * @param authenticationManager El administrador de autenticación utilizado para autenticar usuarios.
     * @param userService El servicio utilizado para gestionar los usuarios.
     * @param tokenProvider El proveedor de tokens JWT.
     */
    @Autowired
    public UserController(AuthenticationManager authenticationManager, IUserService userService, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Autenticar a un usuario.
     *
     * @param loginRequest El objeto LoginRequest que contiene las credenciales del usuario.
     * @return ResponseEntity que contiene la respuesta de inicio de sesión con el token JWT y el ID del usuario.
     */
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
        Long userId = user.getId_usuario();

        return ResponseEntity.ok(new LoginResponse(jwt, userId));
    }

    /**
     * Crear un nuevo usuario.
     *
     * @param user el objeto Use que se va a crear.
     * @return ResponseEntity que contiene el usuario creado o un mensaje de error si ya existe.
     */
    @PostMapping("/registro")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Verificar si el usuario ya existe
        User userExiste = userService.getUserByEmail(user.getEmail());
        if (userExiste != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El usuario '" + user.getEmail() + "' ya existe");
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

    /**
     * Obtener todos los usuarios.
     *
     * @return ResponseEntity que contiene una lista de todos los usuarios.
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Obtener un usuario por su ID.
     *
     * @param id_usuario El ID del usuario a obtener.
     * @return ResponseEntity que contiene el usuario obtenido.
     */
    @GetMapping("/users/{id_usuario}")
    public ResponseEntity<User> getUserById(@PathVariable Long id_usuario) {
        User user = userService.getUserById(id_usuario);
        user.setPassword("PROTECTED");
        user.setId_usuario(id_usuario);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Actualizar un usuario.
     *
     * @param id_usuario El ID del usuario a actualizar.
     * @param updatedUser El objeto User con los datos actualizados.
     * @return ResponseEntity que contiene el usuario actualizado.
     */
    @PutMapping("/users/{id_usuario}")
    public ResponseEntity<User> updateUser(@PathVariable Long id_usuario, @RequestBody User updatedUser) {
        User user = userService.updateUser(id_usuario, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Eliminar un usuario.
     *
     * @param id_usuario El ID del usuario a eliminar.
     * @return ResponseEntity sin contenido.
     */
    @DeleteMapping("/users/{id_usuario}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id_usuario) {
        userService.deleteUser(id_usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}