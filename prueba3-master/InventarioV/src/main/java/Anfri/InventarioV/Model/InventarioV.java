package Anfri.InventarioV.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario_videojuegos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String plataforma;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Double precio;
}