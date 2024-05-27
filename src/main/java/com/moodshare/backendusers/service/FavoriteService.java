package com.moodshare.backendusers.service;

import com.moodshare.backendusers.models.Favorite;
import com.moodshare.backendusers.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public Favorite saveFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByUserId(Long idUsuario) {
        return favoriteRepository.findByIdUsuario(idUsuario);
    }

    public void deleteFavorite(Long idFavorite) {
        favoriteRepository.deleteById(idFavorite);
    }
}
