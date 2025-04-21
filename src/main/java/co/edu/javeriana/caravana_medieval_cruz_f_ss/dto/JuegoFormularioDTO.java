package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class JuegoFormularioDTO {
    private Long id;
    private int tiempoLimiteDeJuego;
    private Integer tiempoTranscurridoDeJuego;
    private double nivelMinimoGananciasJuego;
    private List<Long> caravanaIds;

    public JuegoFormularioDTO() {
    }

    public JuegoFormularioDTO(List<Long> caravanaIds, Long id, double nivelMinimoGananciasJuego, int tiempoLimiteDeJuego, Integer tiempoTranscurridoDeJuego) {
        this.caravanaIds = caravanaIds;
        this.id = id;
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
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

    

    public double getNivelMinimoGananciasJuego() {
        return nivelMinimoGananciasJuego;
    }

    public void setNivelMinimoGananciasJuego(double nivelMinimoGananciasJuego) {
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
    }

    public List<Long> getCaravanaIds() {
        return caravanaIds;
    }

    public void setCaravanaIds(List<Long> caravanaIds) {
        this.caravanaIds = caravanaIds;
    }

    public Integer getTiempoTranscurridoDeJuego() {
        return tiempoTranscurridoDeJuego;
    }

    public void setTiempoTranscurridoDeJuego(Integer tiempoTranscurridoDeJuego) {
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
    }

    
}
