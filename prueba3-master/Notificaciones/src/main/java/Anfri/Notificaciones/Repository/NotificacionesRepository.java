package Anfri.Notificaciones.Repository;

import Anfri.Notificaciones.Model.NotificacionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionesRepository extends JpaRepository<NotificacionesModel, Integer> {
    List<NotificacionesModel> findByClienteId(Integer clienteId);
}