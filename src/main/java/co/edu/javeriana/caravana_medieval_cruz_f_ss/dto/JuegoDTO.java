package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class JuegoDTO {
    private Long id;
    private int tiempoLimiteDeJuego;
    private int tiempoTranscurridoDeJuego;
    private double nivelMinimoGananciasJuego;
    
    public JuegoDTO() {
    }

    public JuegoDTO(Long id, int tiempoLimiteDeJuego, int tiempoTranscurridoDeJuego, double nivelMinimoGananciasJuego) {
        this.id = id;
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
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

    
}
