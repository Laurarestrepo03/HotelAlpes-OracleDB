package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Cliente_excelente {

    @Id
    private String num_documento;
    private String tipo_documento;
    private String nombre;
    private String correo;
    private String tipo;
    private Integer cuenta_reservas;
    private Integer cuenta_consumo;
    private Integer cuenta_r_servicios;

    public Cliente_excelente(){;}

    public Cliente_excelente(String num_documento, String tipo_documento, String nombre, String correo, String tipo,
    Integer cuenta_reservas, Integer cuenta_consumo, Integer cuenta_r_servicios) {

        this.num_documento = num_documento;
        this.tipo_documento = tipo_documento;
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
        this.cuenta_reservas = cuenta_reservas;
        this.cuenta_consumo = cuenta_consumo;
        this.cuenta_r_servicios = cuenta_r_servicios;
    }

    public String getNum_documento() {
        return num_documento;
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

    public Integer getCuenta_reservas() {
        return cuenta_reservas;
    }

    public Integer getCuenta_consumo() {
        return cuenta_consumo;
    }

    public Integer getCuenta_r_servicios() {
        return cuenta_r_servicios;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
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

    public void setCuenta_reservas(Integer cuenta_reservas) {
        this.cuenta_reservas = cuenta_reservas;
    }

    public void setCuenta_consumo(Integer cuenta_consumo) {
        this.cuenta_consumo = cuenta_consumo;
    }

    public void setCuenta_r_servicios(Integer cuenta_r_servicios) {
        this.cuenta_r_servicios = cuenta_r_servicios;
    }

}
