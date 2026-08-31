package Anfri.InventarioV.Service;

import Anfri.InventarioV.DTO.InventarioVRequest;
import Anfri.InventarioV.Model.InventarioV;
import Anfri.InventarioV.Repository.InventarioVRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class InventarioVService {

    @Autowired
    private InventarioVRepository repository;

    public List<InventarioV> listarTodo() {
        log.info("Consultando catálogo de videojuegos de colección");
        return repository.findAll();
    }

    public InventarioV buscarPorId(Integer id) {
        log.info("Buscando videojuego con ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));
    }

    public InventarioV crear(InventarioVRequest request) {
        log.info("Agregando nuevo videojuego al catálogo: {}", request.getNombre());
        InventarioV juego = new InventarioV();
        juego.setNombre(request.getNombre());
        juego.setPlataforma(request.getPlataforma());
        juego.setStock(request.getStock());
        juego.setPrecio(request.getPrecio());
        return repository.save(juego);
    }
}