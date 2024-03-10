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
@Table(name="reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer codigo_reserva;
    private Date fecha_entrada;
    private Date fecha_salida;
    private Integer num_huespedes;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_reserva", referencedColumnName = "num_documento")
    private Usuario cliente_reserva;

    @ManyToOne
    @JoinColumn(name = "habitacion", referencedColumnName = "num_habitacion")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "plan_consumo", referencedColumnName = "tipo")
    private Plan_consumo plan_consumo;

    public Reserva(){;}

    public Reserva(Date fecha_entrada, Date fecha_salida, Integer num_huespedes, String estado,
    Usuario cliente_reserva, Habitacion habitacion, Plan_consumo plan_consumo)
    {
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.num_huespedes = num_huespedes;
        this.estado = estado;
        this.cliente_reserva = cliente_reserva;
        this.habitacion = habitacion;
        this.plan_consumo = plan_consumo;
    }

    public Integer getCodigo_reserva() {
        return codigo_reserva;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public Integer getNum_huespedes() {
        return num_huespedes;
    }

    public String getEstado() {
        return estado;
    }

    public Usuario getCliente_reserva() {
        return cliente_reserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Plan_consumo getPlan_consumo() {
        return plan_consumo;
    }

    public void setCodigo_reserva(Integer codigo_reserva) {
        this.codigo_reserva = codigo_reserva;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public void setNum_huespedes(Integer num_huespedes) {
        this.num_huespedes = num_huespedes;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCliente_reserva(Usuario cliente_reserva) {
        this.cliente_reserva = cliente_reserva;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setPlan_consumo(Plan_consumo plan_consumo) {
        this.plan_consumo = plan_consumo;
    }

}
