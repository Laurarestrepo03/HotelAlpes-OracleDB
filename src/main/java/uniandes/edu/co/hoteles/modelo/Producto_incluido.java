package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="productos_incluidos")
public class Producto_incluido {

    @EmbeddedId
    private Producto_incluidoPK pk;

    public Producto_incluido(){;}

    public  Producto_incluido(Plan_consumo plan_consumo, Producto producto)
    {
        this.pk = new Producto_incluidoPK(plan_consumo, producto);
    }

    public Producto_incluidoPK getPk() {
        return pk;
    }

    public void setPk(Producto_incluidoPK pk) {
        this.pk = pk;
    }
    
}
