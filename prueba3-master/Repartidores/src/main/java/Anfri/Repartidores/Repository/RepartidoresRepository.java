package Anfri.Repartidores.Repository;

import Anfri.Repartidores.Model.RepartidorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepartidoresRepository extends JpaRepository<RepartidorModel, Integer> {
    List<RepartidorModel> findByEstado(String estado);
}