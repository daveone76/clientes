package com.apiclientesv1.service;

import com.apiclientesv1.dto.ClienteDTO;
import com.apiclientesv1.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {


    List<ClienteDTO> obtenerClientes();
    ClienteDTO agregarCliente(ClienteDTO clienteDTO);
    ClienteDTO obtenerPorId (long id);ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
    void eliminar(long id);
    ClienteDTO actualizar(long id, ClienteDTO clienteDTO);
}
