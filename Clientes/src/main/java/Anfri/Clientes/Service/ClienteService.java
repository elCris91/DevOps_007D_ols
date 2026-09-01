package Anfri.Clientes.Service;

import Anfri.Clientes.DTO.ClienteRequest;
import Anfri.Clientes.Model.Cliente;
import Anfri.Clientes.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        log.info("Obteniendo lista de todos los coleccionistas");
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        log.info("Buscando cliente con ID: {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    public Cliente crear(ClienteRequest request) {
        log.info("Registrando nuevo coleccionista: {}", request.getNombre());
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setCorreo(request.getCorreo());
        cliente.setTelefono(request.getTelefono());
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Integer id, ClienteRequest request) {
        log.info("Actualizando datos del cliente ID: {}", id);
        Cliente cliente = buscarPorId(id);
        cliente.setNombre(request.getNombre());
        cliente.setCorreo(request.getCorreo());
        cliente.setTelefono(request.getTelefono());
        return clienteRepository.save(cliente);
    }

    public void eliminar(Integer id) {
        log.info("Eliminando cliente ID: {}", id);
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}