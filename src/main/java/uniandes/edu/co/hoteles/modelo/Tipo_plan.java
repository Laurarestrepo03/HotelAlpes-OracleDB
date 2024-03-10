package uniandes.edu.co.hoteles.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tipos_plan")
public class Tipo_plan {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_tipo;
    private String nombre;
    private String costo_fijo;

    public Tipo_plan(){;}

    public Tipo_plan(String nombre, String costo_fijo){
        this.nombre = nombre;
        this.costo_fijo = costo_fijo;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCosto_fijo() {
        return costo_fijo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCosto_fijo(String costo_fijo) {
        this.costo_fijo = costo_fijo;
    }

}
