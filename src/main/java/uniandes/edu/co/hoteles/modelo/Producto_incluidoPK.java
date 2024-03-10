package uniandes.edu.co.hoteles.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Producto_incluidoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "plan_consumo", referencedColumnName = "tipo")
    private Plan_consumo plan_consumo;

    @ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "id_producto")
    private Producto producto;
    
    public Producto_incluidoPK(){super();}

    public Producto_incluidoPK(Plan_consumo plan_consumo, Producto producto)
    {
        super();
        this.plan_consumo = plan_consumo;
        this.producto = producto;
    }

    public Plan_consumo getPlan_consumo() {
        return plan_consumo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setPlan_consumo(Plan_consumo plan_consumo) {
        this.plan_consumo = plan_consumo;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
