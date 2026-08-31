package Anfri.Resenas.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "resenas")
@AllArgsConstructor
@NoArgsConstructor

public class ResenasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer clienteId;
    private Integer pedidoId;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime fechaResena;
}