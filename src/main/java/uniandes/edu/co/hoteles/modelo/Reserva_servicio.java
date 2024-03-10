package uniandes.edu.co.hoteles.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name="reservas_servicio")
public class Reserva_servicio {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_reserva_servicio;
    private Date dia_reserva;
    private String hora_inicio;
    private String hora_fin;

    @ManyToOne
    @JoinColumn(name = "servicio_comercial", referencedColumnName = "tipo")
    private Servicio_comercial servicio_comercial;

    @ManyToOne
    @JoinColumn(name = "servicio_renta", referencedColumnName = "tipo")
    private Servicio_renta servicio_renta;

    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "num_documento")
    private Usuario cliente;

    public Reserva_servicio(){;}

    public Reserva_servicio(Date dia_reserva, String hora_inicio, String hora_fin,
    Servicio_comercial servicio_comercial, Servicio_renta servicio_renta, Usuario cliente)
    {
        this.dia_reserva = dia_reserva;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.servicio_comercial = servicio_comercial;
        this.servicio_renta = servicio_renta;
        this.cliente = cliente;
    }

    public Integer getId_reserva_servicio() {
        return id_reserva_servicio;
    }

    public Date getDia_reserva() {
        return dia_reserva;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public Servicio_comercial getServicio_comercial() {
        return servicio_comercial;
    }

    public Servicio_renta getServicio_renta() {
        return servicio_renta;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setId_reserva_servicio(Integer id_reserva_servicio) {
        this.id_reserva_servicio = id_reserva_servicio;
    }

    public void setDia_reserva(Date dia_reserva) {
        this.dia_reserva = dia_reserva;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public void setServicio_comercial(Servicio_comercial servicio_comercial) {
        this.servicio_comercial = servicio_comercial;
    }

    public void setServicio_renta(Servicio_renta servicio_renta) {
        this.servicio_renta = servicio_renta;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

}
