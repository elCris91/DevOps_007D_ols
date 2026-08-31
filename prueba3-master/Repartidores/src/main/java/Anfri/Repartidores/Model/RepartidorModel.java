package Anfri.Repartidores.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repartidores")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class RepartidorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String telefono;
    private String tipoVehiculo;
    private String estado;
}