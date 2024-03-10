package uniandes.edu.co.hoteles.modelo_queries;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
public class Consumo_usuario {

    @EmbeddedId
    private Consumo_usuarioPK pk;

    private String cuenta_pagada;
    private  String registrado_por;

    public Consumo_usuario(){;}

    public Consumo_usuario(String usuario, String servicio, Date fecha, String hora, String cuenta_pagada, String registrado_por){
        
        this.pk = new Consumo_usuarioPK(usuario, servicio, fecha, hora);
        this.cuenta_pagada = cuenta_pagada;
        this.registrado_por = registrado_por;
    }

    public Consumo_usuarioPK getPk() {
        return pk;
    }

    public String getCuenta_pagada() {
        return cuenta_pagada;
    }

    public String getRegistrado_por() {
        return registrado_por;
    }

    public void setPk(Consumo_usuarioPK pk) {
        this.pk = pk;
    }

    public void setCuenta_pagada(String cuenta_pagada) {
        this.cuenta_pagada = cuenta_pagada;
    }

    public void setRegistrado_por(String registrado_por) {
        this.registrado_por = registrado_por;
    }

    
}
