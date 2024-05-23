package com.moodshare.backendusers.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "apellido")
    private String apellido;

    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
    )
    private List<Rol> roles;

    public User() { }

    public User(String name, String apellido, String email, String password, List<Rol> roles) {
        this.name = name;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, String name, String apellido, String email, String password, List<Rol> roles) {
        this.id = id;
        this.name = name;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setRoles(List<Rol> role) {
        this.roles = role;
    }
}
