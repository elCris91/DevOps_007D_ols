package Anfri.Promociones.DTO;

import java.time.LocalDateTime;

public class PromocionesRequest {

    private String codigo;
    private String descripcion;
    private Double descuentoPorcentaje;
    private LocalDateTime fechaFin;


    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getDescuentoPorcentaje() { return descuentoPorcentaje; }
    public void setDescuentoPorcentaje(Double descuentoPorcentaje) { this.descuentoPorcentaje = descuentoPorcentaje; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
}
