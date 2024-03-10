package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="dotaciones_incluidas")
public class Dotacion_incluida {

    @EmbeddedId
    private Dotacion_incluidaPK pk;
    private Integer cantidad;

    public Dotacion_incluida(){;}

    public Dotacion_incluida(Tipo_habitacion tipo_habitacion, Dotacion dotacion, Integer cantidad)
    {
        this.pk = new Dotacion_incluidaPK(tipo_habitacion, dotacion);
        this.cantidad = cantidad;
    }

    public Dotacion_incluidaPK getPk() {
        return pk;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setPk(Dotacion_incluidaPK pk) {
        this.pk = pk;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
