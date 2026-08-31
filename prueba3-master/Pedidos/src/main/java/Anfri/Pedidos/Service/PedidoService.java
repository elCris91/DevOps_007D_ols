package Anfri.Pedidos.Service;

import Anfri.Pedidos.DTO.PedidoRequest;
import Anfri.Pedidos.Model.Pedido;
import Anfri.Pedidos.Repository.PedidoRepository;
import Anfri.Pedidos.WebClient.ClienteClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteClient clienteClient;

    @Transactional
    public Pedido procesarPedido(PedidoRequest request) {
        log.info("Validando pedido para Cliente ID: {}", request.getClienteId());


        clienteClient.validarCliente(request.getClienteId());

        log.info("Cliente validado. Artículos solicitados -> F1: {}, Videojuego: {}",
                request.getArticuloFId(), request.getArticuloVId());

        Pedido pedido = new Pedido();
        pedido.setClienteId(request.getClienteId());
        pedido.setArticuloFId(request.getArticuloFId());
        pedido.setArticuloVId(request.getArticuloVId());
        pedido.setCantidad(request.getCantidad());


        return repository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        log.info("Listando todos los pedidos realizados");
        return repository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }
}