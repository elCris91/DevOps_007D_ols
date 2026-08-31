package Anfri.Repartidores.Controller;

import Anfri.Repartidores.DTO.RepartidorRequest;
import Anfri.Repartidores.Model.RepartidorModel;
import Anfri.Repartidores.Service.RepartidoresService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/repartidores")
public class RepartidoresController {

    private final RepartidoresService repartidorService;

    @Autowired
    public RepartidoresController(RepartidoresService repartidorService) {
        this.repartidorService = repartidorService;
    }

    @GetMapping
    public ResponseEntity<List<RepartidorModel>> listar() {
        return ResponseEntity.ok(repartidorService.listarTodos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<RepartidorModel>> listarDisponibles() {
        return ResponseEntity.ok(repartidorService.listarDisponibles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepartidorModel> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(repartidorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RepartidorModel> crear(@Valid @RequestBody RepartidorRequest request) {
        return new ResponseEntity<>(repartidorService.crear(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepartidorModel> actualizar(@PathVariable Integer id, @Valid @RequestBody RepartidorRequest request) {
        return ResponseEntity.ok(repartidorService.actualizar(id, request));
    }

    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<RepartidorModel> cambiarEstado(@PathVariable Integer id, @PathVariable String estado) {
        return ResponseEntity.ok(repartidorService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        repartidorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}