package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombreProducto;
    private double factorOfertaProducto;
    private double factorDemandaProducto;
    
    @OneToMany(mappedBy = "producto")
    private List<CaravanaProducto> enCaravanas;

    @OneToMany(mappedBy = "producto")
    private List<CiudadProducto> enCiudades;


    public Producto() {
    }

    public Producto(List<CaravanaProducto> enCaravanas, List<CiudadProducto> enCiudades, double factorDemandaProducto, double factorOfertaProducto, String nombreProducto) {
        this.enCaravanas = enCaravanas;
        this.enCiudades = enCiudades;
        this.factorDemandaProducto = factorDemandaProducto;
        this.factorOfertaProducto = factorOfertaProducto;
        this.nombreProducto = nombreProducto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getFactorOfertaProducto() {
        return factorOfertaProducto;
    }

    public void setFactorOfertaProducto(double factorOfertaProducto) {
        this.factorOfertaProducto = factorOfertaProducto;
    }

    public double getFactorDemandaProducto() {
        return factorDemandaProducto;
    }

    public void setFactorDemandaProducto(double factorDemandaProducto) {
        this.factorDemandaProducto = factorDemandaProducto;
    }

    public List<CaravanaProducto> getEnCaravanas() {
        return enCaravanas;
    }

    public void setEnCaravanas(List<CaravanaProducto> enCaravanas) {
        this.enCaravanas = enCaravanas;
    }

    public List<CiudadProducto> getEnCiudades() {
        return enCiudades;
    }

    public void setEnCiudades(List<CiudadProducto> enCiudades) {
        this.enCiudades = enCiudades;
    }

    
}
