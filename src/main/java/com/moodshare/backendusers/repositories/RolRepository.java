package com.moodshare.backendusers.repositories;

import com.moodshare.backendusers.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para gestionar la persistencia de la entidad Rol.
 */
public interface RolRepository extends JpaRepository<Rol, Long> {

    /**
     * Encuentra un rol basado en su nombre.
     *
     * @param name el nombre del rol
     * @return el rol encontrado o null si no se encuentra
     */
    Rol findByName(String name);
}
