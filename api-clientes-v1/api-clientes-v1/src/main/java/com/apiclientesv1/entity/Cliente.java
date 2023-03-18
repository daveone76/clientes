package com.apiclientesv1.entity;



public class Cliente {

    private long id;

    private String nombre;

    private String email;

    //CONSTRUCTOR

    public Cliente(long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }


    // GETTERS Y SETTERS

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
