package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="planes_consumo")
public class Plan_consumo {

    @EmbeddedId
    private Plan_consumoPK pk;
    private String genera_descuento;
    private Integer tiempo_estadia;
    private Double valor_descuento_ser; 

    public Plan_consumo(){;}

    public Plan_consumo(Tipo_plan tipo, String genera_descuento, Integer tiempo_estadia, 
    Double valor_descuento_ser)
    {
        this.pk = new Plan_consumoPK(tipo);
        this.genera_descuento = genera_descuento;
        this.tiempo_estadia = tiempo_estadia;
        this.valor_descuento_ser = valor_descuento_ser;
    }

    public Plan_consumoPK getPk() {
        return pk;
    }

    public String getGenera_descuento() {
        return genera_descuento;
    }

    public Integer getTiempo_estadia() {
        return tiempo_estadia;
    }

    public Double getValor_descuento_ser() {
        return valor_descuento_ser;
    }

    public void setPk(Plan_consumoPK pk) {
        this.pk = pk;
    }

    public void setGenera_descuento(String genera_descuento) {
        this.genera_descuento = genera_descuento;
    }

    public void setTiempo_estadia(Integer tiempo_estadia) {
        this.tiempo_estadia = tiempo_estadia;
    }

    public void setValor_descuento_ser(Double valor_descuento_ser) {
        this.valor_descuento_ser = valor_descuento_ser;
    }

}
