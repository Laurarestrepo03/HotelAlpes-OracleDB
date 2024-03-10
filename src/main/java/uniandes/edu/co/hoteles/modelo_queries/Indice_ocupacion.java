package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Indice_ocupacion {

    @Id
    private Integer num_habitacion;
    private Double porcentaje;

    public Indice_ocupacion(){;}

    public Indice_ocupacion(Integer num_habitacion, Double porcentaje){
        this.num_habitacion = num_habitacion;
        this.porcentaje = porcentaje;
    }

    public Integer getNum_habitacion() {
        return num_habitacion;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setNum_habitacion(Integer num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

}
