package Anfri.Resenas.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResenasRequest {
    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer clienteId;

    @NotNull(message = "El ID del pedido es obligatorio")
    private Integer pedidoId;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer calificacion;

    @NotBlank(message = "El comentario es obligatorio")
    private String comentario;
}