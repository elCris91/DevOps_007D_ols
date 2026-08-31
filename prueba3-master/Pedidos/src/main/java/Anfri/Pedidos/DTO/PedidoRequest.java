package Anfri.Pedidos.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoRequest {
    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer clienteId;

    private Integer articuloFId;
    private Integer articuloVId;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;
}