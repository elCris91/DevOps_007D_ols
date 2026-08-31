package Anfri.Pagos.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "notificaciones-service", url = "http://localhost:8084/api/v1/notificaciones")
public interface NotificacionesClient {


    @PostMapping
    Object enviarNotificacion(@RequestBody NotificacionRequest request);

    class NotificacionRequest {
        public Integer clienteId;
        public String mensaje;
        public String tipo;

        public NotificacionRequest(Integer clienteId, String mensaje, String tipo) {
            this.clienteId = clienteId;
            this.mensaje = mensaje;
            this.tipo = tipo;
        }
    }
}