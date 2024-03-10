package uniandes.edu.co.hoteles.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;

@Embeddable
public class ConsumenPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "num_documento")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "servicio", referencedColumnName = "tipo")
    private Servicio servicio;

    private Date fecha;
    private String hora;

    public ConsumenPK(){super();}

    public ConsumenPK(Usuario cliente, Servicio servicio, Date fecha, String hora)
    {
        super();
        this.cliente = cliente;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
