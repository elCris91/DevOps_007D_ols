package Anfri.Envios.Controller;

import Anfri.Envios.DTO.EnvioRequest;
import Anfri.Envios.Model.Envio;
import Anfri.Envios.Service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService service;

    @PostMapping
    public ResponseEntity<Envio> crear(@Valid @RequestBody EnvioRequest request) {
        return new ResponseEntity<>(service.procesarDespacho(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Envio>> listar() {
        return ResponseEntity.ok(service.listarHistorial());
    }
}