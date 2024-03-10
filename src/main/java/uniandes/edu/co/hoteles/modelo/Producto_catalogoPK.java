package uniandes.edu.co.hoteles.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Producto_catalogoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "servicio_comercial", referencedColumnName = "tipo")
    private Servicio_comercial servicio_comercial;

    @ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "id_producto")
    private Producto producto;

    public Producto_catalogoPK(){super();}

    public Producto_catalogoPK(Servicio_comercial servicio_comercial, Producto producto)
    {
        super();
        this.servicio_comercial = servicio_comercial;
        this.producto = producto;
    }

    public Servicio_comercial getServicio_comercial() {
        return servicio_comercial;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setServicio_comercial(Servicio_comercial servicio_comercial) {
        this.servicio_comercial = servicio_comercial;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
