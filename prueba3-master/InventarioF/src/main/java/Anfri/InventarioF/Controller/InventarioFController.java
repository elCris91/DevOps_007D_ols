package Anfri.InventarioF.Controller;

import Anfri.InventarioF.DTO.InventarioFRequest;
import Anfri.InventarioF.Model.InventarioF;
import Anfri.InventarioF.Service.InventarioFService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/f1")
public class InventarioFController {

    @Autowired
    private InventarioFService service;

    @GetMapping
    public ResponseEntity<List<InventarioF>> listar() {
        return ResponseEntity.ok(service.listarTodo());
    }

    @PostMapping
    public ResponseEntity<InventarioF> crear(@Valid @RequestBody InventarioFRequest request) {
        return new ResponseEntity<>(service.crear(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioF> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}