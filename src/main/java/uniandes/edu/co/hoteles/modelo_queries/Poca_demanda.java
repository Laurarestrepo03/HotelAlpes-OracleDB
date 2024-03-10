package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Poca_demanda {
    
    @Id
    private String nombre;
    private Integer semanas;

    public Poca_demanda(){;}

    public Poca_demanda(String nombre, Integer semanas) {
        this.nombre = nombre;
        this.semanas = semanas;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getSemanas() {
        return semanas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSemanas(Integer semanas) {
        this.semanas = semanas;
    }

}
