package com.moodshare.backendusers.models;

import jakarta.persistence.*;

/**
 * Clase para representar una entidad de favorito en la base de datos.
 */
@Entity
@Table(name = "favs_users")
public class Favorite {

    /**
     * ID único del favorito.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fav")
    private Long idFav;

    /**
     * ID del usuario al que pertenece el favorito.
     */
    @Column(name = "id_usuario")
    private Long idUsuario;

    /**
     * ID del elemento marcado como favorito.
     */
    @Column(name = "id_elemento")
    private Long idElemento;

    /**
     * Tipo del elemento marcado como favorito.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_elemento")
    private ElementType tipoElemento;

    public Favorite() { }

    // Getters y Setters
    public Long getIdFav() {
        return idFav;
    }

    public void setIdFav(Long idFav) {
        this.idFav = idFav;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Long idElemento) {
        this.idElemento = idElemento;
    }

    public ElementType getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(ElementType tipoElemento) {
        this.tipoElemento = tipoElemento;
    }
}

/**
 * Tipos de elementos que pueden ser marcados como favoritos.
 */
enum ElementType {
    release,
    master,
    artist
}
