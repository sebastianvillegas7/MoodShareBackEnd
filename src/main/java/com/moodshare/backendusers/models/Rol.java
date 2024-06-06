package com.moodshare.backendusers.models;

import jakarta.persistence.*;

/**
 * Entidad que representa un rol en el sistema.
 */
@Entity
@Table(name = "rol")
public class Rol {

    /**
     * ID único del rol.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;

    /**
     * Nombre del rol.
     */
    private String name;

    public Rol() { }

    /**
     * Constructor que inicializa el nombre del rol.
     *
     * @param name el nombre del rol
     */
    public Rol(String name) {
        this.name = name;
    }

    /**
     * Constructor que inicializa el ID y el nombre del rol.
     *
     * @param id_rol el ID del rol
     * @param name el nombre del rol
     */
    public Rol(Long id_rol, String name) {
        this.id_rol = id_rol;
        this.name = name;
    }

    // Getter y Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
