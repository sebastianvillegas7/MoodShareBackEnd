package com.moodshare.backendusers.service;

import com.moodshare.backendusers.models.Favorite;
import com.moodshare.backendusers.repositories.FavoriteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con los favoritos.
 */
@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    /**
     * Constructor de FavoriteService.
     *
     * @param favoriteRepository El repositorio de favoritos.
     */
    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    /**
     * Guardar un favorito.
     *
     * @param favorite El favorito a guardar.
     * @return El favorito guardado.
     */
    public Favorite saveFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    /**
     * Obtener una lista de todos los favoritos.
     *
     * @return La lista de todos los favoritos de la BBDD.
     */
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    /**
     * Obtener una lista de favoritos del ID usuario proporcionado.
     *
     * @param idUsuario El ID del usuario.
     * @return La lista de favoritos del usuario.
     */
    public List<Favorite> getFavoritesByUserId(Long idUsuario) {
        return favoriteRepository.findByIdUsuario(idUsuario);
    }

    /**
     * Eliminar un favorito.
     *
     * @param idUsuario El ID del usuario.
     * @param idElemento El ID del elemento a eliminar.
     */
    public void deleteFavorite(Long idUsuario, Long idElemento) {
        Favorite favorite = favoriteRepository.findByIdUsuarioAndIdElemento(idUsuario, idElemento);
        if (favorite != null) {
            favoriteRepository.delete(favorite);
        } else {
            throw new EntityNotFoundException("Favorite not found with idUsuario: " + idUsuario + " and idElemento: " + idElemento);
        }
    }
}
