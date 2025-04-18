package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Juego {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int tiempoTranscurridoDeJuego;
    private int tiempoLimiteDeJuego;
    private double nivelMinimoGananciasJuego;

    @OneToMany
    private List<Caravana> caravanas;

    @OneToMany
    private List<Jugador> jugadores;

    
    public Juego() {
    }

    public Juego(List<Caravana> caravanas, List<Jugador> jugadores, double nivelMinimoGananciasJuego, int tiempoLimiteDeJuego, int tiempoTranscurridoDeJuego) {
        this.caravanas = caravanas;
        this.jugadores = jugadores;
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
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

    public List<Caravana> getCaravanas() {
        return caravanas;
    }

    public void setCaravanas(List<Caravana> caravanas) {
        this.caravanas = caravanas;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }


   
}
