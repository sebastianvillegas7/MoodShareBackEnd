package com.moodshare.backendusers.models;

import jakarta.persistence.*;

@Entity
@Table(name = "favs_users")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fav")
    private Long idFav;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_elemento")
    private Long idElemento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_elemento")
    private ElementType tipoElemento;

    public Favorite() { }

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

enum ElementType {
    release,
    master,
    artist
}
