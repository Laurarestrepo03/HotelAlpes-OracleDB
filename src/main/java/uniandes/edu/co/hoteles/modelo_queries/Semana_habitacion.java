package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Semana_habitacion {

    @Id
    private Date inicio_semana;
    private Date fin_semana;
    private Integer num_habitacion;

    public Semana_habitacion(){;}

    public Semana_habitacion(Date inicio_semana, Date fin_semana, Integer num_habitacion){
        this.inicio_semana = inicio_semana;
        this.fin_semana = fin_semana;
        this.num_habitacion = num_habitacion;
    }

    public Date getInicio_semana() {
        return inicio_semana;
    }

    public Date getFin_semana() {
        return fin_semana;
    }

    public Integer getNum_habitacion() {
        return num_habitacion;
    }

    public void setInicio_semana(Date inicio_semana) {
        this.inicio_semana = inicio_semana;
    }

    public void setFin_semana(Date fin_semana) {
        this.fin_semana = fin_semana;
    }

    public void setNum_habitacion(Integer num_habitacion) {
        this.num_habitacion = num_habitacion;
    }
   
}
