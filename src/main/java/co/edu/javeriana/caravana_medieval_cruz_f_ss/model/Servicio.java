package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoServicio tipo;

    private double precioServicio;

    public enum TipoServicio {
        REPARAR,
        MEJORAR_CAPACIDAD,
        MEJORAR_VELOCIDAD,
        GUARDIAS
    }

    public Servicio() {
    }

    public Servicio(long id, TipoServicio tipo, double precioServicio) {
        this.id = id;
        this.tipo = tipo;
        this.precioServicio = precioServicio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoServicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoServicio tipo) {
        this.tipo = tipo;
    }

    public double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }  
    
}
