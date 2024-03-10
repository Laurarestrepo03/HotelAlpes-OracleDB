package uniandes.edu.co.hoteles.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tipos_servicio")
public class Tipo_servicio {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_tipo;
    private String nombre;

    public Tipo_servicio(){;}

    public Tipo_servicio(String nombre)
    {
        this.nombre = nombre;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
