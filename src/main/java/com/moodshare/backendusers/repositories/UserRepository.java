package com.moodshare.backendusers.repositories;

import com.moodshare.backendusers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar la persistencia de la entidad User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Encuentra un usuario basado en su email.
     *
     * @param email el email del usuario
     * @return el usuario encontrado o null si no se encuentra
     */
    User findByEmail(String email);
}
