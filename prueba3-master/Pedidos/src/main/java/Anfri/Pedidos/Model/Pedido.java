package Anfri.Pedidos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer clienteId;

    private Integer articuloFId;
    private Integer articuloVId;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String estado;

    private LocalDateTime fechaPedido;

    @PrePersist
    public void asignarValores() {
        this.fechaPedido = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }
}