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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteRequest requestMock;
    private Cliente clienteGuardadoMock;

    @BeforeEach
    void setUp() {

        requestMock = new ClienteRequest();
        requestMock.setNombre("Bruce Wayne");
        requestMock.setCorreo("bruce@gotham.com");
        requestMock.setTelefono("555-BATMAN");


        clienteGuardadoMock = new Cliente();
        clienteGuardadoMock.setId(1);
        clienteGuardadoMock.setNombre("Bruce Wayne");
        clienteGuardadoMock.setCorreo("bruce@gotham.com");
        clienteGuardadoMock.setTelefono("555-BATMAN");
    }

    @Test
    void crear_DeberiaMapearDatosYGuardarCorrectamente() {

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteGuardadoMock);


        Cliente resultado = clienteService.crear(requestMock);


        assertNotNull(resultado, "El cliente guardado no debe ser nulo");
        assertEquals("Bruce Wayne", resultado.getNombre(), "El nombre debe coincidir");
        assertEquals("bruce@gotham.com", resultado.getCorreo(), "El correo debe coincidir");
        assertEquals("555-BATMAN", resultado.getTelefono(), "El teléfono debe coincidir");

        
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }
}