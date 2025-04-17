package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity
public class Ciudad {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombreCiudad;
    private double impuestosDeEntradaCiudad;
    
    public Ciudad() {
    }

    public Ciudad(long id, String nombreCiudad, double impuestosDeEntradaCiudad) {
        this.id = id;
        this.nombreCiudad = nombreCiudad;
        this.impuestosDeEntradaCiudad = impuestosDeEntradaCiudad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public double getImpuestosDeEntradaCiudad() {
        return impuestosDeEntradaCiudad;
    }

    public void setImpuestosDeEntradaCiudad(double impuestosDeEntradaCiudad) {
        this.impuestosDeEntradaCiudad = impuestosDeEntradaCiudad;
    }
}
