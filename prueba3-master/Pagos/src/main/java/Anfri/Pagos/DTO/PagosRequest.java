package Anfri.Pagos.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PagosRequest {
    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer clienteId;

    @NotNull(message = "El ID del pedido es obligatorio")
    private Integer pedidoId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private Double monto;

    @NotNull(message = "El método de pago es obligatorio")
    private String metodoPago;
}