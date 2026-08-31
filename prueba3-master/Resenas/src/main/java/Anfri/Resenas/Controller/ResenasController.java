package Anfri.Resenas.Controller;

import Anfri.Resenas.DTO.ResenasRequest;
import Anfri.Resenas.Model.ResenasModel;
import Anfri.Resenas.Service.ResenasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenasController {

    @Autowired
    private ResenasService resenasService;

    @GetMapping
    public ResponseEntity<List<ResenasModel>> listar() {
        return ResponseEntity.ok(resenasService.listarTodas());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<ResenasModel>> listarPorPedido(@PathVariable Integer pedidoId) {
        return ResponseEntity.ok(resenasService.listarPorPedido(pedidoId));
    }

    @PostMapping
    public ResponseEntity<ResenasModel> crear(@Valid @RequestBody ResenasRequest request) {
        return new ResponseEntity<>(resenasService.crearResena(request), HttpStatus.CREATED);
    }
}