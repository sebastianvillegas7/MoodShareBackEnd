package com.moodshare.backendusers.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;

    private String name;

    public Rol() { }

    public Rol(String name) {
        this.name = name;
    }

    public Rol(Long id_rol, String name) {
        this.id_rol = id_rol;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
