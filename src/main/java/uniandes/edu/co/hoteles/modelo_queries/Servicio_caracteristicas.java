package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Servicio_caracteristicas {

    @Id
    private String nombre;
    private Integer capacidad;
    private String tipo_cobro;
    private Double precio;

    public Servicio_caracteristicas(){;}

    public Servicio_caracteristicas(String nombre, Integer capcacidad, String tipo_cobro, Double precio) {
        this.nombre = nombre;
        this.capacidad = capcacidad;
        this.tipo_cobro = tipo_cobro;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
