package Anfri.Clientes;

import Anfri.Clientes.DTO.ClienteRequest;
import Anfri.Clientes.Model.Cliente;
import Anfri.Clientes.Repository.ClienteRepository;
import Anfri.Clientes.Service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// tests de listar, buscar, actualizar y eliminar (tambien cuando el cliente no existe)
@ExtendWith(MockitoExtension.class)
class ClienteServiceCasosCriticosTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteExistente;

    @BeforeEach
    void setUp() {
        clienteExistente = new Cliente(1, "Ana Perez", "ana@duocuc.cl", "912345678");
    }

    @Test
    void listarTodos_DeberiaRetornarTodosLosClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(clienteExistente));

        List<Cliente> resultado = clienteService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("ana@duocuc.cl", resultado.get(0).getCorreo());
    }

    @Test
    void buscarPorId_ClienteExiste_DeberiaRetornarlo() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(clienteExistente));

        Cliente resultado = clienteService.buscarPorId(1);

        assertEquals("Ana Perez", resultado.getNombre());
    }

    @Test
    void buscarPorId_ClienteNoExiste_DeberiaLanzarExcepcion() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException error = assertThrows(RuntimeException.class,
                () -> clienteService.buscarPorId(99));

        assertTrue(error.getMessage().contains("99"));
    }

    @Test
    void actualizar_DeberiaCambiarLosDatosYGuardar() {
        ClienteRequest cambios = new ClienteRequest("Ana Soto", "ana.soto@duocuc.cl", "987654321");
        when(clienteRepository.findById(1)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(inv -> inv.getArgument(0));

        Cliente resultado = clienteService.actualizar(1, cambios);

        assertEquals("Ana Soto", resultado.getNombre());
        assertEquals("ana.soto@duocuc.cl", resultado.getCorreo());
        assertEquals("987654321", resultado.getTelefono());
        verify(clienteRepository).save(clienteExistente);
    }

    @Test
    void actualizar_ClienteNoExiste_NoDeberiaGuardarNada() {
        ClienteRequest cambios = new ClienteRequest("X", "x@duocuc.cl", "900000000");
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clienteService.actualizar(99, cambios));
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void eliminar_ClienteExiste_DeberiaBorrarlo() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(clienteExistente));

        clienteService.eliminar(1);

        verify(clienteRepository, times(1)).delete(clienteExistente);
    }

    @Test
    void eliminar_ClienteNoExiste_NoDeberiaBorrarNada() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clienteService.eliminar(99));
        verify(clienteRepository, never()).delete(any());
    }
}
