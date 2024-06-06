package com.moodshare.backendusers.service;

import com.moodshare.backendusers.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Interfaz de servicio para gestionar operaciones relacionadas con usuarios.
 * Extiende {@link UserDetailsService} para la integración con Spring Security.
 */
public interface IUserService extends UserDetailsService {

    /**
     * Guarda un nuevo usuario.
     *
     * @param user el usuario a guardar
     * @return el usuario guardado
     */
    User save(User user);

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return la lista de todos los usuarios
     */
    List<User> getAllUsers();

    /**
     * Obtiene un usuario basado en su ID.
     *
     * @param id el ID del usuario
     * @return el usuario encontrado o null si no se encuentra
     */
    User getUserById(Long id);

    /**
     * Obtiene un usuario basado en su email.
     *
     * @param email el email del usuario
     * @return el usuario encontrado o null si no se encuentra
     */
    User getUserByEmail(String email);

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param id el ID del usuario a actualizar
     * @param updatedUser el usuario con la información actualizada
     * @return el usuario actualizado
     */
    User updateUser(Long id, User updatedUser);

    /**
     * Elimina un usuario basado en su ID.
     *
     * @param id el ID del usuario a eliminar
     */
    void deleteUser(Long id);
}
