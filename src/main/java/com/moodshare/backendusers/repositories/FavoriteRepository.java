package com.moodshare.backendusers.repositories;

import com.moodshare.backendusers.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByIdUsuario(Long id_usuario);

    Favorite findByIdUsuarioAndIdElemento(Long idUsuario, Long idElemento);
}
