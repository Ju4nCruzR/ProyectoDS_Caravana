package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CiudadServicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean servicioAdquirido = false;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToOne
    private Servicio servicio;

    public CiudadServicio() {
    }

    public CiudadServicio(Ciudad ciudad, Servicio servicio) {
        this.ciudad = ciudad;
        this.servicio = servicio;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

}
