package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ruta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double distanciaRuta;
    private boolean esSeguraRuta;
    private int danoRuta;
    
    public Ruta() {
    }

    public Ruta(long id, double distanciaRuta, boolean esSeguraRuta, int danoRuta) {
        this.id = id;
        this.distanciaRuta = distanciaRuta;
        this.esSeguraRuta = esSeguraRuta;
        this.danoRuta = danoRuta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDistanciaRuta() {
        return distanciaRuta;
    }

    public void setDistanciaRuta(double distanciaRuta) {
        this.distanciaRuta = distanciaRuta;
    }

    public boolean isEsSeguraRuta() {
        return esSeguraRuta;
    }

    public void setEsSeguraRuta(boolean esSeguraRuta) {
        this.esSeguraRuta = esSeguraRuta;
    }

    public int getDanoRuta() {
        return danoRuta;
    }

    public void setDanoRuta(int danoRuta) {
        this.danoRuta = danoRuta;
    }

}
