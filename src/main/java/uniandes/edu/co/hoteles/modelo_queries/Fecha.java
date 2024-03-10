package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Fecha {
    
    @Id
    private Date fecha;
    private Integer cantidad;

    public Fecha (){;}

    public Fecha(Date fecha, Integer cantidad){
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
