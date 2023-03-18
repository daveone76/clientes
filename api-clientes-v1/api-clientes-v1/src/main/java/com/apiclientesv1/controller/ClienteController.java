package com.apiclientesv1.controller;


import com.apiclientesv1.dto.ClienteDTO;
import com.apiclientesv1.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        List<ClienteDTO> listaClientes = clienteService.obtenerClientes();
        return new ResponseEntity<>(listaClientes, HttpStatus.OK);
    }

    /**
     * Agrega un nuevo cliente
     * Asigna un ID nuevo al cliente que se va a guardar.
     */
    @PostMapping
    public ResponseEntity<ClienteDTO> guardarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevoClienteDTO = clienteService.agregarCliente(clienteDTO);
            return new ResponseEntity<>(nuevoClienteDTO, HttpStatus.CREATED);
    }

    /**
     * Obtiene un cliente por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable("id") long id) {
        return new ResponseEntity<>(clienteService.obtenerPorId(id), HttpStatus.OK);
    }

    /**
     * Elimina un cliente  por su id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable("id") long id) {
        clienteService.eliminar(id);
        return new ResponseEntity<>("Se elimio el cliente correctamente", HttpStatus.OK);
    }

    /**
     * Actualiza los datos de un cliente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable("id") long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteRespuesta = clienteService.actualizar(id, clienteDTO);
        return new ResponseEntity<>(clienteRespuesta, HttpStatus.CREATED);
    }

}//FIN DE LA CLASE
