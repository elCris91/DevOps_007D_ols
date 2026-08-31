package Anfri.Envios.Service;

import Anfri.Envios.DTO.EnvioRequest;
import Anfri.Envios.Model.Envio;
import Anfri.Envios.Repository.EnvioRepository;
import Anfri.Envios.WebClient.PedidoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EnvioService {

    @Autowired
    private EnvioRepository repository;

    @Autowired
    private PedidoClient pedidoClient;

    @Transactional
    public Envio procesarDespacho(EnvioRequest request) {
        log.info("Iniciando proceso de despacho para Pedido ID: {}", request.getPedidoId());

        try {
            Map<String, Object> datosPedido = pedidoClient.obtenerPedidoPorId(request.getPedidoId());
            log.info("Pedido validado correctamente. Datos: {}", datosPedido);
        } catch (Exception e) {
            log.error("Error al validar pedido: {}", e.getMessage());
            throw new RuntimeException("No se puede generar el envio: Pedido no encontrado o error de red");
        }

        Envio envio = new Envio();
        envio.setPedidoId(request.getPedidoId());
        envio.setDireccion(request.getDireccion());

        return repository.save(envio);
    }

    public List<Envio> listarHistorial() {
        log.info("Consultando historial de envios");
        return repository.findAll();
    }
}