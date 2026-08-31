package Anfri.InventarioF.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario_f1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String escuderia;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Double precio;
}