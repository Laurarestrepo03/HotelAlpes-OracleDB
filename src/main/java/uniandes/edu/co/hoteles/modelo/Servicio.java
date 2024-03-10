package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="servicios")
public class Servicio {

    @EmbeddedId
    private ServicioPK pk;
    private Integer capacidad;
    private String tipo_cobro;
    private Double precio;

    public Servicio(){;}

    public Servicio(Tipo_servicio tipo, Integer capacidad, String tipo_cobro, Double precio)
    {
        this.pk = new ServicioPK(tipo);
        this.capacidad = capacidad;
        this.tipo_cobro = tipo_cobro;
        this.precio = precio;
    }

    public ServicioPK getPk() {
        return pk;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getTipo_cobro() {
        return tipo_cobro;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPk(ServicioPK pk) {
        this.pk = pk;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setTipo_cobro(String tipo_cobro) {
        this.tipo_cobro = tipo_cobro;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
