package Anfri.Promociones.Repository;

import Anfri.Promociones.Model.PromocionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PromocionesRepository extends JpaRepository<PromocionesModel, Integer> {

    Optional<PromocionesModel> findByCodigo(String codigo);
}
