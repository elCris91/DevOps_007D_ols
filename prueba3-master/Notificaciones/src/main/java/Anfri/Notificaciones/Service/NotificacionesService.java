package Anfri.Notificaciones.Service;

import Anfri.Notificaciones.DTO.NotificacionesRequest;
import Anfri.Notificaciones.Model.NotificacionesModel;
import Anfri.Notificaciones.Repository.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    public List<NotificacionesModel> listarTodas() {
        return notificacionesRepository.findAll();
    }

    public List<NotificacionesModel> listarPorCliente(Integer clienteId) {
        return notificacionesRepository.findByClienteId(clienteId);
    }

    public NotificacionesModel crearNotificacion(NotificacionesRequest request) {
        NotificacionesModel notificacion = new NotificacionesModel();
        notificacion.setClienteId(request.getClienteId());
        notificacion.setMensaje(request.getMensaje());
        notificacion.setTipo(request.getTipo());
        notificacion.setEstado("ENVIADO");
        notificacion.setFechaEnvio(LocalDateTime.now());

        return notificacionesRepository.save(notificacion);
    }
}