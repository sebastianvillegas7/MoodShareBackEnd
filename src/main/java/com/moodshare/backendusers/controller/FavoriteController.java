package com.moodshare.backendusers.controller;

import com.moodshare.backendusers.models.Favorite;
import com.moodshare.backendusers.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favs")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite favorite) {
        Favorite savedFavorite = favoriteService.saveFavorite(favorite);
        return ResponseEntity.ok(savedFavorite);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<Favorite>> getFavoritesByUserId(@PathVariable Long idUsuario) {
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(idUsuario);
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/delete/{idFavorite}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long idFavorite) {
        favoriteService.deleteFavorite(idFavorite);
        return ResponseEntity.noContent().build();
    }
}
