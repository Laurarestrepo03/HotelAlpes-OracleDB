package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Buen_cliente {

    @Id
    private String cliente;
    private Double semanas;
    private Double suma_consumo;

    public Buen_cliente (){;}

    public Buen_cliente(String cliente, Double semanas, Double suma_consumo){
        this.cliente = cliente;
        this.semanas = semanas;
        this.suma_consumo = suma_consumo;
    }

    public String getCliente() {
        return cliente;
    }

    public Double getSemanas() {
        return semanas;
    }

    public Double getSuma_consumo() {
        return suma_consumo;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setCantidad(Double semanas) {
        this.semanas = semanas;
    }

    public void setSuma_consumo(Double suma_consumo) {
        this.suma_consumo = suma_consumo;
    }
    
}
