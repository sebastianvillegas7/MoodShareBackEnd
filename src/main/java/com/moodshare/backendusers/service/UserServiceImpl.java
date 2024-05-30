package com.moodshare.backendusers.service;

import com.moodshare.backendusers.models.Rol;
import com.moodshare.backendusers.models.User;
import com.moodshare.backendusers.repositories.RolRepository;
import com.moodshare.backendusers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar las operaciones relacionadas con los usuarios.
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RolRepository rolRepository;

    /**
     * Constructor de UserServiceImpl.
     *
     * @param userRepository El repositorio de usuarios.
     * @param passwordEncoder El codificador de contraseñas.
     */
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Guardar un usuario.
     *
     * @param user El objeto User a guardar.
     * @return El usuario guardado.
     */
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Buscar el rol "USER"
        Rol userRole = rolRepository.findByName("USER");

        // Asignar el rol "USER" al nuevo usuario
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    /**
     * Obtener todos los usuarios.
     *
     * @return La lista de todos los usuarios.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Obtener un usuario por su ID.
     *
     * @param id_usuario El ID del usuario.
     * @return El usuario correspondiente al ID, o null si no se encuentra.
     */
    public User getUserById(Long id_usuario) {
        Optional<User> optionalUser = userRepository.findById(id_usuario);
        return optionalUser.orElse(null);
    }

    /**
     * Obtener un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario.
     * @return El usuario correspondiente al correo electrónico.
     */
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Actualizar un usuario existente.
     *
     * @param id_usuario El ID del usuario a actualizar.
     * @param updatedUser El objeto User con los datos actualizados.
     * @return El usuario actualizado, o null si no se encuentra.
     */
    public User updateUser(Long id_usuario, User updatedUser) {
        User user = getUserById(id_usuario);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setApellido(updatedUser.getApellido());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Eliminar un usuario por su ID.
     *
     * @param id_usuario El ID del usuario.
     */
    public void deleteUser(Long id_usuario) {
        userRepository.deleteById(id_usuario);
    }

    /**
     * Cargar los detalles de un usuario por su nombre de usuario (correo electrónico).
     *
     * @param email El correo electrónico del usuario.
     * @return Los detalles del usuario.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}