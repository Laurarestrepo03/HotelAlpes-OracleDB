package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="servicios_renta")
public class Servicio_renta {
    
    @EmbeddedId
    private Servicio_rentaPK pk;
    private Double costo_por_hora;
    private Double costo_adicional;
    
    public Servicio_renta(){;}

    public Servicio_renta(Servicio tipo, Double costo_por_hora, Double costo_adicional)
    {
        this.pk = new Servicio_rentaPK(tipo);
        this.costo_por_hora = costo_por_hora;
        this.costo_adicional = costo_adicional;
    }

    public Servicio_rentaPK getPk() {
        return pk;
    }

    public Double getCosto_por_hora() {
        return costo_por_hora;
    }

    public Double getCosto_adicional() {
        return costo_adicional;
    }

    public void setPk(Servicio_rentaPK pk) {
        this.pk = pk;
    }

    public void setCosto_por_hora(Double costo_por_hora) {
        this.costo_por_hora = costo_por_hora;
    }

    public void setCosto_adicional(Double costo_adicional) {
        this.costo_adicional = costo_adicional;
    }

}