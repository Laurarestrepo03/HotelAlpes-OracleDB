package uniandes.edu.co.hoteles.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tipos_habitacion")
public class Tipo_habitacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_tipo;
    private String nombre;
    private Integer capacidad;

    public Tipo_habitacion(){;}

    public Tipo_habitacion(String nombre, Integer capacidad)
    {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString()
    {
        return this.id_tipo+"";
    }
    
}
