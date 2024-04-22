package com.moodshare.backendusers.dto;

public class UserRegistroDTO {

    private Long id;
    private String name;
    private String apellido;
    private String email;
    private String password;

    public UserRegistroDTO() { }

    public UserRegistroDTO(String email) {
        this.email = email;
    }

    public UserRegistroDTO(String name, String apellido, String email, String password) {
        this.name = name;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public UserRegistroDTO(Long id, String name, String apellido, String email, String password) {
        this.id = id;
        this.name = name;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
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
}
