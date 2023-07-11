package com.example.webapp.modelos;

public class Cliente {
    private String nombre;
    private String email;
    private String primerApellido;
    private String segundoApellido;
    private String contrasenia;
    private Long id;
    private Long idDireccion;

    public Cliente(String nombre, String email, String primerApellido, String segundoApellido, String contrasenia, Long id, Long idDireccion) {
        this.nombre = nombre;
        this.email = email;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.contrasenia = contrasenia;
        this.id = id;
        this.idDireccion = idDireccion;
    }

    public Cliente(){
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
