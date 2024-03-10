package uniandes.edu.co.hoteles.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
public class ServicioPK implements Serializable {

    @OneToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id_tipo")
    private Tipo_servicio tipo;

    public ServicioPK(){super();}

    public ServicioPK(Tipo_servicio tipo)
    {
        super();
        this.tipo = tipo;
    }

    public Tipo_servicio getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_servicio tipo) {
        this.tipo = tipo;
    }
    
}
