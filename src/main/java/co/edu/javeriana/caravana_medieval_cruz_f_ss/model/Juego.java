package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Juego {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int tiempoTranscurridoDeJuego;
    private int tiempoLimiteDeJuego;
    private double nivelMinimoGananciasJuego;
    
    public Juego() {
    }

    public Juego(long id, int tiempoTranscurridoDeJuego, int tiempoLimiteDeJuego, double nivelMinimoGananciasJuego) {
        this.id = id;
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTiempoTranscurridoDeJuego() {
        return tiempoTranscurridoDeJuego;
    }

    public void setTiempoTranscurridoDeJuego(int tiempoTranscurridoDeJuego) {
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
    }

    public int getTiempoLimiteDeJuego() {
        return tiempoLimiteDeJuego;
    }

    public void setTiempoLimiteDeJuego(int tiempoLimiteDeJuego) {
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
    }

    public double getNivelMinimoGananciasJuego() {
        return nivelMinimoGananciasJuego;
    }

    public void setNivelMinimoGananciasJuego(double nivelMinimoGananciasJuego) {
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
    }

    
}
