package uniandes.edu.co.hoteles.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
public class Servicio_comercialPK implements Serializable {
    
    @OneToOne
    @JoinColumn(name = "tipo", referencedColumnName = "tipo")
    private Servicio tipo;

    public Servicio_comercialPK(){super();}

    public Servicio_comercialPK(Servicio tipo)
    {
        super();
        this.tipo = tipo;
    }

    public Servicio getTipo() {
        return tipo;
    }

    public void setTipo(Servicio tipo) {
        this.tipo = tipo;
    }
    
}
