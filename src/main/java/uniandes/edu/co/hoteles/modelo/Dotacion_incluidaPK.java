package uniandes.edu.co.hoteles.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Dotacion_incluidaPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "tipo_habitacion", referencedColumnName = "id_tipo")
    private Tipo_habitacion tipo_habitacion;

    @ManyToOne
    @JoinColumn(name = "dotacion", referencedColumnName = "id_dotacion")
    private Dotacion dotacion;

    public Dotacion_incluidaPK(){super();}

    public Dotacion_incluidaPK(Tipo_habitacion tipo_habitacion, Dotacion dotacion)
    {
        super();
        this.tipo_habitacion = tipo_habitacion;
        this.dotacion = dotacion;
    }

    public Tipo_habitacion getTipo_habitacion() {
        return tipo_habitacion;
    }

    public Dotacion getDotacion() {
        return dotacion;
    }

    public void setTipo_habitacion(Tipo_habitacion tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public void setDotacion(Dotacion dotacion) {
        this.dotacion = dotacion;
    }
    
}
