package com.moodshare.backendusers.models;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad que representa un usuario en el sistema.
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    /**
     * ID único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    /**
     * Nombre del usuario.
     */
    @Column(name = "name")
    private String name;

    /**
     * Apellido del usuario.
     */
    @Column(name = "apellido")
    private String apellido;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * Lista de roles asociados al usuario.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id_rol")
    )
    private List<Rol> roles;

    public User() { }

    /**
     * Constructor que inicializa los campos del usuario.
     *
     * @param name el nombre del usuario
     * @param apellido el apellido del usuario
     * @param email el correo electrónico del usuario
     * @param password la contraseña del usuario
     * @param roles la lista de roles del usuario
     */
    public User(String name, String apellido, String email, String password, List<Rol> roles) {
        this.name = name;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Constructor que inicializa los campos del usuario sin roles.
     *
     * @param name el nombre del usuario
     * @param apellido el apellido del usuario
     * @param email el correo electrónico del usuario
     * @param password la contraseña del usuario
     */
    public User(String name, String apellido, String email, String password) {
        this.name = name;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}
