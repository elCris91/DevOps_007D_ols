package Anfri.Promociones;

import Anfri.Promociones.Model.PromocionesModel;
import Anfri.Promociones.Repository.PromocionesRepository;
import Anfri.Promociones.Service.PromocionesService;
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
class PromocionesServiceTest {

    @Mock
    private PromocionesRepository promocionesRepository;

    @InjectMocks
    private PromocionesService promocionesService;

    private PromocionesModel promocionNuevaMock;
    private PromocionesModel promocionGuardadaMock;

    @BeforeEach
    void setUp() {

        promocionNuevaMock = new PromocionesModel();
        promocionNuevaMock.setCodigo("VERANO2026");


        promocionGuardadaMock = new PromocionesModel();
        promocionGuardadaMock.setCodigo("VERANO2026");
        promocionGuardadaMock.setActivo(true);
    }

    @Test
    void guardarPromocion_DeberiaSetearActivoEnTrue_YGuardarCorrectamente() {

        when(promocionesRepository.save(any(PromocionesModel.class))).thenReturn(promocionGuardadaMock);


        PromocionesModel resultado = promocionesService.guardarPromocion(promocionNuevaMock);


        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertEquals("VERANO2026", resultado.getCodigo(), "El código debe mantenerse igual");
        assertEquals(true, resultado.getActivo(), "¡Regla de negocio cumplida! La promoción debe estar ACTIVA (true)");


        verify(promocionesRepository, times(1)).save(any(PromocionesModel.class));
    }
}