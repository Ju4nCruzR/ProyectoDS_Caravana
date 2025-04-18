package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ruta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double distanciaRuta;
    private boolean esSeguraRuta;
    private int danoRuta;
    
    @ManyToOne
    private Ciudad ciudadOrigen;

    @ManyToOne
    private Ciudad ciudadDestino;

    public Ruta() {
    }

    public Ruta(Ciudad ciudadDestino, Ciudad ciudadOrigen, int danoRuta, double distanciaRuta, boolean esSeguraRuta) {
        this.ciudadDestino = ciudadDestino;
        this.ciudadOrigen = ciudadOrigen;
        this.danoRuta = danoRuta;
        this.distanciaRuta = distanciaRuta;
        this.esSeguraRuta = esSeguraRuta;
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

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

}
