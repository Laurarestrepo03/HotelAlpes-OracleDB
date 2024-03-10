package uniandes.edu.co.hoteles.modelo_queries;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import java.sql.Date;

@Embeddable
public class Consumo_clientePK implements Serializable {

    private String num_documento;
    private Date fecha;

    public Consumo_clientePK(){super();}

    public Consumo_clientePK(String num_documento, Date fecha) {
        this.num_documento = num_documento;
        this.fecha = fecha;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
