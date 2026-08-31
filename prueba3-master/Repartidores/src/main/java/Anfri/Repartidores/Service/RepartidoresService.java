package Anfri.Repartidores.Service;

import Anfri.Repartidores.DTO.RepartidorRequest;
import Anfri.Repartidores.Model.RepartidorModel;
import Anfri.Repartidores.Repository.RepartidoresRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@Slf4j
public class RepartidoresService {

    private final RepartidoresRepository repartidorRepository;

    @Autowired
    public RepartidoresService(RepartidoresRepository repartidorRepository) {
        this.repartidorRepository = repartidorRepository;
    }

    public List<RepartidorModel> listarTodos() {
        log.info("Listando todos los repartidores");
        return repartidorRepository.findAll();
    }

    public List<RepartidorModel> listarDisponibles() {
        log.info("Listando repartidores disponibles");
        return repartidorRepository.findByEstado("DISPONIBLE");
    }

    public RepartidorModel buscarPorId(Integer id) {
        log.info("Buscando repartidor con ID: {}", id);
        return repartidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repartidor no encontrado con id: " + id));
    }

    public RepartidorModel crear(RepartidorRequest request) {
        log.info("Registrando nuevo repartidor: {}", request.getNombre());
        RepartidorModel repartidor = new RepartidorModel();
        repartidor.setNombre(request.getNombre());
        repartidor.setTelefono(request.getTelefono());
        repartidor.setTipoVehiculo(request.getTipoVehiculo());
        repartidor.setEstado("DISPONIBLE");
        return repartidorRepository.save(repartidor);
    }

    public RepartidorModel actualizar(Integer id, RepartidorRequest request) {
        log.info("Actualizando datos del repartidor ID: {}", id);
        RepartidorModel repartidor = buscarPorId(id);
        repartidor.setNombre(request.getNombre());
        repartidor.setTelefono(request.getTelefono());
        repartidor.setTipoVehiculo(request.getTipoVehiculo());
        return repartidorRepository.save(repartidor);
    }

    public RepartidorModel cambiarEstado(Integer id, String nuevoEstado) {
        RepartidorModel repartidor = buscarPorId(id);
        if (repartidor.getEstado().equalsIgnoreCase(nuevoEstado)) {
            throw new RuntimeException("El repartidor ya se encuentra en estado: " + nuevoEstado);
        }
        log.info("Cambiando estado de repartidor {} a {}", id, nuevoEstado);
        repartidor.setEstado(nuevoEstado.toUpperCase());
        return repartidorRepository.save(repartidor);
    }

    public void eliminar(Integer id) {
        log.info("Eliminando repartidor ID: {}", id);
        RepartidorModel repartidor = buscarPorId(id);
        repartidorRepository.delete(repartidor);
    }
}