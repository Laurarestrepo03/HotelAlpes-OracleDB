package uniandes.edu.co.hoteles.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="dotaciones")
public class Dotacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_dotacion;
    private String nombre;

    public Dotacion(){;}

    public Dotacion(String nombre)
    {
        this.nombre = nombre;
    }

    public Integer getId_dotacion() {
        return id_dotacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_dotacion(Integer id_dotacion) {
        this.id_dotacion = id_dotacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
