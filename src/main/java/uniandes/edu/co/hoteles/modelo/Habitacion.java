package uniandes.edu.co.hoteles.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="habitaciones")
public class Habitacion {

    @Id
    private Integer num_habitacion;
    private Double costo_noche;

    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id_tipo")
    private Tipo_habitacion tipo;

    public Habitacion(){;}

    public Habitacion(Integer num_habitacion, Double costo_noche, Tipo_habitacion tipo)
    {
        this.num_habitacion = num_habitacion;
        this.costo_noche = costo_noche;
        this.tipo = tipo;
    }

    public Integer getNum_habitacion() {
        return num_habitacion;
    }

    public Double getCosto_noche() {
        return costo_noche;
    }

    public Tipo_habitacion getTipo() {
        return tipo;
    }

    public void setNum_habitacion(Integer num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public void setCosto_noche(Double costo_noche) {
        this.costo_noche = costo_noche;
    }

    public void setTipo(Tipo_habitacion tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString()
    {
        return this.num_habitacion+"";
    }

}
