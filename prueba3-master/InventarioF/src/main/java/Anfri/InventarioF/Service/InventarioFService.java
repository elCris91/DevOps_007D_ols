package Anfri.InventarioF.Service;

import Anfri.InventarioF.DTO.InventarioFRequest;
import Anfri.InventarioF.Model.InventarioF;
import Anfri.InventarioF.Repository.InventarioFRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class InventarioFService {

    @Autowired
    private InventarioFRepository repository;

    public List<InventarioF> listarTodo() {
        log.info("Consultando catálogo de coleccionables F1");
        return repository.findAll();
    }

    public InventarioF buscarPorId(Integer id) {
        log.info("Buscando producto F1 con ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto F1 no encontrado"));
    }

    public InventarioF crear(InventarioFRequest request) {
        log.info("Agregando nuevo artículo F1: {}", request.getNombre());
        InventarioF item = new InventarioF();
        item.setNombre(request.getNombre());
        item.setEscuderia(request.getEscuderia());
        item.setStock(request.getStock());
        item.setPrecio(request.getPrecio());
        return repository.save(item);
    }
}