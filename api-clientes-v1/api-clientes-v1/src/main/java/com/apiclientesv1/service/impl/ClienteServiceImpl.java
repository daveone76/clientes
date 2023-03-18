package com.apiclientesv1.service.impl;

import com.apiclientesv1.Repository.ClienteRepository;
import com.apiclientesv1.dto.ClienteDTO;
import com.apiclientesv1.entity.Cliente;
import com.apiclientesv1.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> obtenerClientes() {
        List<ClienteDTO> listDTO = new ArrayList<>();
        List<Cliente> lista = clienteRepository.obtenerClientes();
        lista.forEach(n -> {
           listDTO.add(toDTO(n));
        });
        return listDTO;
    }
    @Override
    public ClienteDTO agregarCliente(ClienteDTO clienteDTO) {
        Cliente clienteGuardado = clienteRepository.guardar(toEntity(clienteDTO));
        ClienteDTO guardadoDTO = toDTO(clienteGuardado);
        return guardadoDTO;
    }
    @Override
    public ClienteDTO obtenerPorId (long id){
        Cliente cliente = clienteRepository.obtenerPorId(id).orElseThrow(
                () -> new RuntimeException("Cliente con id: " + id + " no encontrado"));
      ClienteDTO clienteRespuesta = toDTO(cliente);
        return clienteRespuesta;
    }

    @Override
    public void eliminar(long id) {
        clienteRepository.obtenerPorId(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Cliente con ID: " + id + " no encontrado");
        });
        clienteRepository.eliminar(id);
    }


    @Override
    public ClienteDTO actualizar(long id, ClienteDTO clienteDTO) {
       Cliente cliente = clienteRepository.obtenerPorId(id).orElseThrow(
                () -> new RuntimeException("Cliente con id: " + id + " no encontrado"));
       if (clienteDTO.getNombre() != null && clienteDTO.getEmail() != null){
           cliente.setNombre(clienteDTO.getNombre());
           cliente.setEmail(clienteDTO.getEmail());
       }
       ClienteDTO clienteDtoRespuesta = toDTO(clienteRepository.actualizar(cliente));
       return clienteDtoRespuesta;
    }

    @Override
    public ClienteDTO toDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getNombre(), cliente.getEmail());
        clienteDTO.setId(cliente.getId());
        return clienteDTO;
    }

    //Metodo para convertir un cliente DTO a entidad
    @Override
    public Cliente toEntity(ClienteDTO clienteDTO){

        return new Cliente(clienteDTO.getId(),clienteDTO.getNombre(),clienteDTO.getEmail());
    }

}//fin