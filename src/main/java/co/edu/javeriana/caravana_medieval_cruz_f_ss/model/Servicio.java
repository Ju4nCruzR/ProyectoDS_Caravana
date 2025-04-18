package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoServicio tipo;

    private double precioServicio;

    @OneToMany(mappedBy = "servicio")
    private List<CiudadServicio> ciudades;

    public Servicio() {
    }

    public Servicio(List<CiudadServicio> ciudades, double precioServicio, TipoServicio tipo) {
        this.ciudades = ciudades;
        this.precioServicio = precioServicio;
        this.tipo = tipo;
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

    public List<CiudadServicio> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CiudadServicio> ciudades) {
        this.ciudades = ciudades;
    }

    public enum TipoServicio {
        REPARAR,
        MEJORAR_CAPACIDAD,
        MEJORAR_VELOCIDAD,
        GUARDIAS
    }
}
