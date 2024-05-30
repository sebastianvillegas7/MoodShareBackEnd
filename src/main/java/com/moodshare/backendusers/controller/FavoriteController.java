package com.moodshare.backendusers.controller;

import com.moodshare.backendusers.models.Favorite;
import com.moodshare.backendusers.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar los favoritos de los usuarios.
 */
@RestController
@RequestMapping("/api/favs")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    /**
     * Añadir un nuevo favorito.
     *
     * @param favorite El elemento favorito que se va a añadir.
     * @return ResponseEntity que contiene el favorito guardado.
     */
    @PostMapping
    public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite favorite) {
        Favorite savedFavorite = favoriteService.saveFavorite(favorite);
        return ResponseEntity.ok(savedFavorite);
    }

    /**
     * Obtener todos los favoritos de un usuario específico.
     *
     * @param idUsuario El ID del usuario cuyos favoritos se van a obtener.
     * @return ResponseEntity que contiene una lista de favoritos del usuario.
     */
    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<Favorite>> getFavoritesByUserId(@PathVariable Long idUsuario) {
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(idUsuario);
        return ResponseEntity.ok(favorites);
    }

    /**
     * Eliminar un favorito.
     *
     * @param idFavorite El ID del favorito que se va a eliminar.
     * @return ResponseEntity sin contenido.
     */
    @DeleteMapping("/delete/{idFavorite}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long idFavorite) {
        favoriteService.deleteFavorite(idFavorite);
        return ResponseEntity.noContent().build();
    }
}
