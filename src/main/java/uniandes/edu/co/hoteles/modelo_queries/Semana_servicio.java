package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Semana_servicio {

    @Id
    private Date inicio_semana;
    private Date fin_semana;
    private String nombre;

    public Semana_servicio(){;}

    public Semana_servicio(Date inicio_semana, Date fin_semana, String nombre){
        this.inicio_semana = inicio_semana;
        this.fin_semana = fin_semana;
        this.nombre = nombre;
    }

    public Date getInicio_semana() {
        return inicio_semana;
    }

    public Date getFin_semana() {
        return fin_semana;
    }

    public String getNombre() {
        return nombre;
    }

    public void setInicio_semana(Date inicio_semana) {
        this.inicio_semana = inicio_semana;
    }

    public void setFin_semana(Date fin_semana) {
        this.fin_semana = fin_semana;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
}
