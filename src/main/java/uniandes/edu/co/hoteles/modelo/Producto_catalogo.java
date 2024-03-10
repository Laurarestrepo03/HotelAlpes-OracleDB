package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="catalogo_productos")
public class Producto_catalogo {

    @EmbeddedId
    private Producto_catalogoPK pk;

    public Producto_catalogo(){;}

    public Producto_catalogo(Servicio_comercial servicio_comercial, Producto producto)
    {
        this.pk = new Producto_catalogoPK(servicio_comercial, producto);
    }

    public Producto_catalogoPK getPk() {
        return pk;
    }

    public void setPk(Producto_catalogoPK pk) {
        this.pk = pk;
    }
    
}
