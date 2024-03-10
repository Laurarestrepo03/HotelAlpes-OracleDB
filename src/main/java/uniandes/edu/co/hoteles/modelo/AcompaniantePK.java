package uniandes.edu.co.hoteles.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class AcompaniantePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "reserva", referencedColumnName = "codigo_reserva")
    private Reserva reserva;

    private String num_documento;

    public AcompaniantePK(){super();}

    public AcompaniantePK(Reserva reserva, String num_documento)
    {
        super();
        this.reserva = reserva;
        this.num_documento = num_documento;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }
}
