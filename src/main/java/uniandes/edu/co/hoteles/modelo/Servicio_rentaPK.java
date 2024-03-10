package uniandes.edu.co.hoteles.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
public class Servicio_rentaPK implements Serializable {

    @OneToOne
    @JoinColumn(name = "tipo", referencedColumnName = "tipo")
    private Servicio tipo;

    public Servicio_rentaPK(){super();}

    public Servicio_rentaPK(Servicio tipo)
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
