package Anfri.InventarioF.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InventarioFRequest {

        @NotBlank(message = "El nombre del artículo de F1 es obligatorio")
        private String nombre;

        @NotBlank(message = "La escudería es obligatoria")
        private String escuderia;

        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        private Integer stock;

        @NotNull(message = "El precio es obligatorio")
        @Min(value = 1, message = "El precio debe ser mayor a 0")
        private Double precio;
    }

