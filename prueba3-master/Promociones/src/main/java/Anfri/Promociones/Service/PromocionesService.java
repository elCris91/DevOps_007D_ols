package Anfri.Promociones.Service;

import Anfri.Promociones.Model.PromocionesModel;
import Anfri.Promociones.Repository.PromocionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PromocionesService {

    private final PromocionesRepository promocionesRepository;

    @Autowired
    public PromocionesService(PromocionesRepository promocionesRepository) {
        this.promocionesRepository = promocionesRepository;
    }

    public List<PromocionesModel> listarTodas() {
        return promocionesRepository.findAll();
    }

    public PromocionesModel buscarPorCodigo(String codigo) {
        return promocionesRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("La promoción con código " + codigo + " no existe."));
    }

    public PromocionesModel guardarPromocion(PromocionesModel promocion) {
        promocion.setActivo(true);
        return promocionesRepository.save(promocion);
    }
}