package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombreProducto;
    private double factorOfertaProducto;
    private double factorDemandaProducto;
    
    public Producto() {
    }

    public Producto(long id, String nombreProducto, double factorOfertaProducto, double factorDemandaProducto) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.factorOfertaProducto = factorOfertaProducto;
        this.factorDemandaProducto = factorDemandaProducto;
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

    
}
