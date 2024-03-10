package uniandes.edu.co.hoteles.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    private String num_documento;
    private String tipo_documento;
    private String nombre;
    private String correo;

    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id_tipo")
    private Tipo_usuario tipo;

    public Usuario(){;}

    public Usuario(String num_documento, String tipo_documento, String nombre, String correo, 
    Tipo_usuario tipo)
    {
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

    public Tipo_usuario getTipo() {
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

    public void setTipo(Tipo_usuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString()
    {
        return this.num_documento+"";
    }

}
