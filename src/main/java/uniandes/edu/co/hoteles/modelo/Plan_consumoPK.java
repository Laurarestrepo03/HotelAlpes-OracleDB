package uniandes.edu.co.hoteles.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
public class Plan_consumoPK implements Serializable {

    @OneToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id_tipo")
    private Tipo_plan tipo;

    public Plan_consumoPK(){super();}

    public Plan_consumoPK(Tipo_plan tipo)
    {
        super();
        this.tipo = tipo;
    }

    public Tipo_plan getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_plan tipo) {
        this.tipo = tipo;
    }
    
}
