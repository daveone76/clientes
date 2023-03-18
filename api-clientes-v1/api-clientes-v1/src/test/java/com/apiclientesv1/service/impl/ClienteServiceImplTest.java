package com.apiclientesv1.service.impl;

import com.apiclientesv1.dto.ClienteDTO;
import com.apiclientesv1.service.ClienteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceImplTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testObtenerClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerClientes();
        Assert.assertNotNull(clientes);
    }

    @Test
    public void testAgregarCliente() {
        ClienteDTO clienteDTO = new ClienteDTO("Pedro", "pedro@gmail.com");
        ClienteDTO clienteGuardadoDTO = clienteService.agregarCliente(clienteDTO);
        Assert.assertNotNull(clienteGuardadoDTO.getId());
        Assert.assertEquals(clienteDTO.getNombre(), clienteGuardadoDTO.getNombre());
        Assert.assertEquals(clienteDTO.getEmail(), clienteGuardadoDTO.getEmail());
    }

    @Test
    public void testObtenerPorId() {
        ClienteDTO clienteDTO = new ClienteDTO("Pedro", "pedro@gmail.com");
        ClienteDTO clienteGuardadoDTO = clienteService.agregarCliente(clienteDTO);
        Long id = clienteGuardadoDTO.getId();
        ClienteDTO clienteObtenidoDTO = clienteService.obtenerPorId(id);
        Assert.assertEquals(clienteDTO.getNombre(), clienteObtenidoDTO.getNombre());
        Assert.assertEquals(clienteDTO.getEmail(), clienteObtenidoDTO.getEmail());
    }

    @Test(expected = RuntimeException.class)
    public void testObtenerPorIdNoEncontrado() {
        Long id = 12345L;
        ClienteDTO clienteObtenidoDTO = clienteService.obtenerPorId(id);
    }

    @Test
    public void testEliminar() {
        ClienteDTO clienteDTO = new ClienteDTO("pedro", "pedro@gmail.com");
        ClienteDTO clienteGuardadoDTO = clienteService.agregarCliente(clienteDTO);
        Long id = clienteGuardadoDTO.getId();
        clienteService.eliminar(id);
        try {
            clienteService.obtenerPorId(id);
            Assert.fail("Se esperaba una excepción de cliente no encontrado");
        } catch (RuntimeException e) {
            Assert.assertEquals("Cliente con id: " + id + " no encontrado", e.getMessage());
        }
    }

    @Test
    public void testActualizar() {
        ClienteDTO clienteDTO = new ClienteDTO("sofia", "sofia@gmail.com");
        ClienteDTO clienteGuardadoDTO = clienteService.agregarCliente(clienteDTO);
        Long id = clienteGuardadoDTO.getId();
        ClienteDTO clienteActualizadoDTO = new ClienteDTO("sofia", "sofia@gmail.com");
        clienteService.actualizar(id, clienteActualizadoDTO);
        ClienteDTO clienteObtenidoDTO = clienteService.obtenerPorId(id);
        Assert.assertEquals(clienteActualizadoDTO.getNombre(), clienteObtenidoDTO.getNombre());
        Assert.assertEquals(clienteActualizadoDTO.getEmail(), clienteObtenidoDTO.getEmail());
    }

    @Test(expected = RuntimeException.class)
    public void testActualizarNoEncontrado() {
        Long id = 12345L;
        ClienteDTO clienteDTO = new ClienteDTO("juan", "juan@gmail.com");
        clienteService.actualizar(id, clienteDTO);
    }

}