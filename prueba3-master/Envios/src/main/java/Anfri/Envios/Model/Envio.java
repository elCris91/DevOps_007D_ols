package Anfri.Envios.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "envios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoId;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String estado;

    private LocalDateTime fechaEnvio;

    @PrePersist
    public void configurarEnvio() {
        this.fechaEnvio = LocalDateTime.now();
        this.estado = "EN PREPARACION";
    }
}