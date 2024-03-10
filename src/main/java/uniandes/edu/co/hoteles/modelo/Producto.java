package uniandes.edu.co.hoteles.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_producto;
    private String nombre;
    private Double precio;

    public Producto(){;}

    public Producto(String nombre, Double precio)
    {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}
