package Anfri.Pagos.Controller;

import Anfri.Pagos.DTO.PagosRequest;
import Anfri.Pagos.Model.PagosModel;
import Anfri.Pagos.Service.PagosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagosController {

    @Autowired
    private PagosService pagoService;

    @GetMapping
    public ResponseEntity<List<PagosModel>> listar() {
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagosModel> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PagosModel> crear(@Valid @RequestBody PagosRequest request) {
        return new ResponseEntity<>(pagoService.procesarPago(request), HttpStatus.CREATED);
    }
}