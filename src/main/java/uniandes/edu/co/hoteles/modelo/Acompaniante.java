package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="acompaniantes")
public class Acompaniante {
    
    @EmbeddedId
    private AcompaniantePK pk;
    private String tipo_documento;

    public Acompaniante(){;}

    public Acompaniante(Reserva reserva, String num_documento, String tipo_documento)
    {
        this.pk = new AcompaniantePK(reserva, num_documento);
        this.tipo_documento = tipo_documento;
    }

    public AcompaniantePK getPk() {
        return pk;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setPk(AcompaniantePK pk) {
        this.pk = pk;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

}
