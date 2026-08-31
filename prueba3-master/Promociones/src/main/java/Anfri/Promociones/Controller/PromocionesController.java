package Anfri.Promociones.Controller;

import Anfri.Promociones.Model.PromocionesModel;
import Anfri.Promociones.Service.PromocionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promociones")
public class PromocionesController {

    private final PromocionesService promocionesService;

    @Autowired
    public PromocionesController(PromocionesService promocionesService) {
        this.promocionesService = promocionesService;
    }

    @GetMapping
    public List<PromocionesModel> obtenerTodas() {
        return promocionesService.listarTodas();
    }

    @GetMapping("/buscar/{codigo}")
    public PromocionesModel obtenerPorCodigo(@PathVariable String codigo) {
        return promocionesService.buscarPorCodigo(codigo);
    }

    @PostMapping
    public PromocionesModel crearPromocion(@RequestBody PromocionesModel promocion) {
        return promocionesService.guardarPromocion(promocion);
    }
}