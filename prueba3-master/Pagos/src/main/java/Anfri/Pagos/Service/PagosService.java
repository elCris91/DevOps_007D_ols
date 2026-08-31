package Anfri.Pagos.Service;

import Anfri.Pagos.Client.NotificacionesClient;
import Anfri.Pagos.DTO.PagosRequest;
import Anfri.Pagos.Model.PagosModel;
import Anfri.Pagos.Repository.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagosService {

    @Autowired
    private PagosRepository pagosRepository;

    @Autowired
    private NotificacionesClient notificacionesClient;

    public List<PagosModel> listarTodos() {
        return pagosRepository.findAll();
    }

    public PagosModel buscarPorId(Integer id) {
        return pagosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public PagosModel procesarPago(PagosRequest request) {
        PagosModel pagos = new PagosModel();
        pagos.setClienteId(request.getClienteId());
        pagos.setPedidoId(request.getPedidoId());
        pagos.setMonto(request.getMonto());
        pagos.setMetodoPago(request.getMetodoPago());
        pagos.setEstado("APROBADO");
        pagos.setFechaPago(LocalDateTime.now());

        PagosModel pagoGuardado = pagosRepository.save(pagos);


        try {
            NotificacionesClient.NotificacionRequest notificacion =
                    new NotificacionesClient.NotificacionRequest(
                            pagoGuardado.getClienteId(),
                            "Tu pago de $" + pagoGuardado.getMonto() + " ha sido APROBADO.",
                            "EMAIL"
                    );
            notificacionesClient.enviarNotificacion(notificacion);
            System.out.println("¡Notificación enviada con éxito!");
        } catch (Exception e) {
            System.out.println("Error al avisar a Notificaciones: " + e.getMessage());
        }
        // ------------------------------------------------

        return pagoGuardado;
    }
}