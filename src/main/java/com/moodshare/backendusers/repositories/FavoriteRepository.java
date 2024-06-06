package com.moodshare.backendusers.repositories;

import com.moodshare.backendusers.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestionar la persistencia de la entidad Favorite.
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    /**
     * Encuentra todos los favoritos de un usuario específico.
     *
     * @param id_usuario el ID del usuario
     * @return una lista de favoritos del usuario
     */
    List<Favorite> findByIdUsuario(Long id_usuario);

    /**
     * Encuentra un favorito específico basado en el ID del usuario y el ID del elemento.
     *
     * @param idUsuario  el ID del usuario
     * @param idElemento el ID del elemento
     * @return el favorito encontrado o null si no se encuentra
     */
    Favorite findByIdUsuarioAndIdElemento(Long idUsuario, Long idElemento);
}
