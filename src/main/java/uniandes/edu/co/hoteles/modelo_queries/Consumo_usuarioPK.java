package uniandes.edu.co.hoteles.modelo_queries;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import java.sql.Date;

@Embeddable
public class Consumo_usuarioPK implements Serializable {

    private String usuario;
    private String servicio;
    private Date fecha;
    private String hora;

    public Consumo_usuarioPK(){super();}

    public Consumo_usuarioPK(String usuario, String servicio, Date fecha, String hora) {
        this.usuario = usuario;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getServicio() {
        return servicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
  
}
