package uniandes.edu.co.hoteles.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="consumen")
public class Consumen {

    @EmbeddedId
    private ConsumenPK pk;
    private String cuenta_pagada;

    @ManyToOne
    @JoinColumn(name = "registrado_por", referencedColumnName = "num_documento")
    private Usuario registrado_por;

    public Consumen(){;}
    
    public Consumen(Usuario cliente, Servicio servicio, Date fecha, String hora, String cuenta_pagada, Usuario registrado_por)
    {
        this.pk = new ConsumenPK(cliente, servicio, fecha, hora);
        this.cuenta_pagada = cuenta_pagada;
        this.registrado_por = registrado_por;
    }

    public ConsumenPK getPk() {
        return pk;
    }

    public String getCuenta_pagada() {
        return cuenta_pagada;
    }

    public Usuario getRegistrado_por() {
        return registrado_por;
    }

    public void setPk(ConsumenPK pk) {
        this.pk = pk;
    }

    public void setCuenta_pagada(String cuenta_pagada) {
        this.cuenta_pagada = cuenta_pagada;
    }

    public void setRegistrado_por(Usuario registrado_por) {
        this.registrado_por = registrado_por;
    }
}
