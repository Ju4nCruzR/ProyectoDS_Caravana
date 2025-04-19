package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class JuegoFormularioDTO {
    private int tiempoLimiteDeJuego;
    private double nivelMinimoGananciasJuego;
    private List<Long> caravanaIds;

    public JuegoFormularioDTO() {
    }

    public JuegoFormularioDTO(int tiempoLimiteDeJuego, double nivelMinimoGananciasJuego, List<Long> caravanaIds) {
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
        this.caravanaIds = caravanaIds;
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

    
}
