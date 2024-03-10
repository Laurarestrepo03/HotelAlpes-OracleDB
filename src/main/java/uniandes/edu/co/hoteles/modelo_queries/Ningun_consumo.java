package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Ningun_consumo {

    @Id
    private String num_documento;
    private String tipo_documento;
    private String nombre;
    private String correo;
    private String tipo;

    public Ningun_consumo(){;}

    public Ningun_consumo(String num_documento, String tipo_documento, String nombre, String correo, String tipo) {
        this.num_documento = num_documento;
        this.tipo_documento = tipo_documento;
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
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
    
}
