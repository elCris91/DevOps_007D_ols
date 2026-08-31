package Anfri.InventarioV.Controller;


import Anfri.InventarioV.DTO.InventarioVRequest;
import Anfri.InventarioV.Model.InventarioV;
import Anfri.InventarioV.Service.InventarioVService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videojuegos")
public class InventarioVController {

    @Autowired
    private InventarioVService service;

    @GetMapping
    public ResponseEntity<List<InventarioV>> listar() {
        return ResponseEntity.ok(service.listarTodo());
    }

    @PostMapping
    public ResponseEntity<InventarioV> crear(@Valid @RequestBody InventarioVRequest request) {
        return new ResponseEntity<>(service.crear(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioV> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}