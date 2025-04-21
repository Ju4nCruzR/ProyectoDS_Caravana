package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class JuegoDetalleDTO {
    private Long id;
    private int tiempoLimiteDeJuego;
    private int tiempoTranscurridoDeJuego;
    private double nivelMinimoGananciasJuego;
    private List<CaravanaResumenDTO> caravanas;
    private List<JugadorResumenDTO> jugadores;

    public JuegoDetalleDTO() {
    }

    public JuegoDetalleDTO(Long id, int tiempoLimiteDeJuego, int tiempoTranscurridoDeJuego, double nivelMinimoGananciasJuego,
                           List<CaravanaResumenDTO> caravanas, List<JugadorResumenDTO> jugadores) {
        this.id = id;
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
        this.caravanas = caravanas;
        this.jugadores = jugadores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTiempoLimiteDeJuego() {
        return tiempoLimiteDeJuego;
    }

    public void setTiempoLimiteDeJuego(int tiempoLimiteDeJuego) {
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
    }

    public int getTiempoTranscurridoDeJuego() {
        return tiempoTranscurridoDeJuego;
    }

    public void setTiempoTranscurridoDeJuego(int tiempoTranscurridoDeJuego) {
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
    }

    public double getNivelMinimoGananciasJuego() {
        return nivelMinimoGananciasJuego;
    }

    public void setNivelMinimoGananciasJuego(double nivelMinimoGananciasJuego) {
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
    }

    public List<CaravanaResumenDTO> getCaravanas() {
        return caravanas;
    }

    public void setCaravanas(List<CaravanaResumenDTO> caravanas) {
        this.caravanas = caravanas;
    }

    public List<JugadorResumenDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorResumenDTO> jugadores) {
        this.jugadores = jugadores;
    }
}
