package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="servicios_comerciales")
public class Servicio_comercial {

    @EmbeddedId
    private Servicio_comercialPK pk;
    private String estilo;
    private String requiere_reserva;

    public Servicio_comercial(){;}

    public Servicio_comercial(Servicio servicio, String estilo, String requiere_reserva)
    {
        this.pk = new Servicio_comercialPK(servicio);
        this.estilo = estilo;
        this.requiere_reserva = requiere_reserva;
    }

    public Servicio_comercialPK getPk() {
        return pk;
    }

    public String getEstilo() {
        return estilo;
    }

    public String getRequiere_reserva() {
        return requiere_reserva;
    }

    public void setPk(Servicio_comercialPK pk) {
        this.pk = pk;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public void setRequiere_reserva(String requiere_reserva) {
        this.requiere_reserva = requiere_reserva;
    }

}
