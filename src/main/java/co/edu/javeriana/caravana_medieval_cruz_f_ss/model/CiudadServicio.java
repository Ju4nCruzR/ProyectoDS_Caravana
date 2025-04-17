package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CiudadServicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean servicioAdquirido = false;

    public CiudadServicio() {
    }

    public CiudadServicio(long id, boolean servicioAdquirido) {
        this.id = id;
        this.servicioAdquirido = servicioAdquirido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isServicioAdquirido() {
        return servicioAdquirido;
    }

    public void setServicioAdquirido(boolean servicioAdquirido) {
        this.servicioAdquirido = servicioAdquirido;
    }

    
}
