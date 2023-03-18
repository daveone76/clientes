package com.apiclientesv1.Repository;


import com.apiclientesv1.entity.Cliente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


//Capa dedicada a la persistencia de datos

@Repository  //@Repository indica que es un componente con la semantica de repositorio
public class ClienteRepository {

    static final String FILENAME = "clientes.json";
    static final String PATH_FILE = "C:\\Users\\odva7\\Downloads\\api-clientes-v1\\api-clientes-v1\\";
    private ObjectMapper objectMapper;
    private List<Cliente> clientes;


    @Autowired
    public ClienteRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        try {
            File file = ResourceUtils.getFile(PATH_FILE + FILENAME);
            this.clientes = objectMapper.readValue(file, new TypeReference<List<Cliente>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo " + FILENAME, e);
        }
    }

    public List<Cliente> obtenerClientes() throws RuntimeException {
        return clientes;
    }

    public Cliente guardar( Cliente cliente) {
        Long maxId = clientes.stream()
                .mapToLong(Cliente::getId)
                .max()
                .orElse(0L);
        cliente.setId(maxId + 1);
        clientes.add(cliente);
        Cliente clienteGuardado = clientes.stream()
                .filter(c -> Objects.equals(c.getId(), cliente.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El cliente no se guardó correctamente."));
        return clienteGuardado;
    }

    public Optional<Cliente> obtenerPorId(long id) {
        Optional<Cliente> cliente =
                clientes.stream()
                        .filter(c -> Objects.equals(c.getId(), id)).findFirst();
        return cliente;
    }

    public void eliminar(long id) {
        try {
            clientes.removeIf(c -> Objects.equals(c.getId(), id));
            objectMapper.writeValue(ResourceUtils.getFile(PATH_FILE + FILENAME), clientes);
        } catch (IOException e) {
            throw new RuntimeException("Error al intentar eliminar el cliente con id " + id + " del archivo " + FILENAME, e);
        }
    }

    public Cliente actualizar(Cliente cliente) {
        try {
            Optional<Cliente> clienteEncontrado = obtenerPorId(cliente.getId());
            if (clienteEncontrado.isPresent()) {
                Cliente clienteActualizado = clienteEncontrado.get();
                clienteActualizado.setNombre(cliente.getNombre());
                clienteActualizado.setEmail(cliente.getEmail());
                objectMapper.writeValue(ResourceUtils.getFile(PATH_FILE + FILENAME), clientes);
                return clienteActualizado;
            } else {
                throw new RuntimeException("No se encontró el cliente con id: " + cliente.getId());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar los datos en el archivo clientes.json", e);
        }
    }

}//Fin de la clase ClienteRepository
