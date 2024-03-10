package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Dinero_recolectado {

    @Id
    private Integer num_habitacion;
    private Double dinero;

    public Dinero_recolectado(){;}
    
    public Dinero_recolectado(Integer num_habitacion, Double dinero)
    {
        this.num_habitacion = num_habitacion;
        this.dinero = dinero;
    }

    public Integer getNum_habitacion() {
        return num_habitacion;
    }

    public Double getDinero() {
        return dinero;
    }

    public void setNum_habitacion(Integer num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public void setDinero(Double dinero) {
        this.dinero = dinero;
    }
    
}
