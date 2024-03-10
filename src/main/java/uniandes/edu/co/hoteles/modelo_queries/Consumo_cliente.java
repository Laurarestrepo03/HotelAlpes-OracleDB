package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
public class Consumo_cliente {

    @EmbeddedId
    private Consumo_clientePK pk;
    private String tipo_documento;
    private String nombre;
    private String correo;
    private String tipo;
    private Integer veces_consumo;

    public Consumo_cliente(){;}

    public Consumo_cliente(String num_documento, String tipo_documento, String nombre, String correo, String tipo, 
    Integer veces_consumo, Date fecha) {
        
        this.pk = new Consumo_clientePK(num_documento, fecha);
        this.tipo_documento = tipo_documento;
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
        this.veces_consumo = veces_consumo;
    }

    public Consumo_clientePK getPk() {
        return pk;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getVeces_consumo() {
        return veces_consumo;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public void setPk(Consumo_clientePK pk) {
        this.pk = pk;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setVeces_consumo(Integer veces_consumo) {
        this.veces_consumo = veces_consumo;
    }

}
