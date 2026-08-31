package Anfri.Repartidores;

import Anfri.Repartidores.DTO.RepartidorRequest;
import Anfri.Repartidores.Model.RepartidorModel;
import Anfri.Repartidores.Repository.RepartidoresRepository; 
import Anfri.Repartidores.Service.RepartidoresService;
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
class RepartidoresServiceTest {

    @Mock
    private RepartidoresRepository repartidoresRepository;

    @InjectMocks
    private RepartidoresService repartidoresService;

    private RepartidorRequest requestMock;
    private RepartidorModel repartidorGuardadoMock;

    @BeforeEach
    void setUp() {
        requestMock = new RepartidorRequest();
        requestMock.setNombre("Juan Perez");
        requestMock.setTelefono("123456789");
        requestMock.setTipoVehiculo("Moto");

        repartidorGuardadoMock = new RepartidorModel();
        repartidorGuardadoMock.setId(1);
        repartidorGuardadoMock.setNombre("Juan Perez");
        repartidorGuardadoMock.setTelefono("123456789");
        repartidorGuardadoMock.setTipoVehiculo("Moto");
        repartidorGuardadoMock.setEstado("DISPONIBLE");
    }

    @Test
    void crear_DeberiaGuardarYRetornarRepartidor_ConEstadoDisponible() {
        when(repartidoresRepository.save(any(RepartidorModel.class))).thenReturn(repartidorGuardadoMock);

        RepartidorModel resultado = repartidoresService.crear(requestMock);

        assertNotNull(resultado, "El repartidor devuelto no debería ser nulo");
        assertEquals("Juan Perez", resultado.getNombre(), "El nombre debe guardarse correctamente");
        assertEquals("DISPONIBLE", resultado.getEstado(), "¡Regla de negocio cumplida! El estado inicial debe ser DISPONIBLE");

        verify(repartidoresRepository, times(1)).save(any(RepartidorModel.class));
    }
}