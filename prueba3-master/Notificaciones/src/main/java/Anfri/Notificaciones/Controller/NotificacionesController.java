package Anfri.Notificaciones.Controller;

import Anfri.Notificaciones.DTO.NotificacionesRequest;
import Anfri.Notificaciones.Model.NotificacionesModel;
import Anfri.Notificaciones.Service.NotificacionesService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionesController {

    @Autowired
    private NotificacionesService notificacionesService;

    @GetMapping
    public ResponseEntity<List<NotificacionesModel>> listar() {
        return ResponseEntity.ok(notificacionesService.listarTodas());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<NotificacionesModel>> listarPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(notificacionesService.listarPorCliente(clienteId));
    }

    @PostMapping
    public ResponseEntity<NotificacionesModel> crear(@Valid @RequestBody NotificacionesRequest request) {
        return new ResponseEntity<>(notificacionesService.crearNotificacion(request), HttpStatus.CREATED);
    }
}