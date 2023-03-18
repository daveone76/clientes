package com.apiclientesv1.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private long id;

    private String nombre;

    private String email;

    public ClienteDTO(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
}
