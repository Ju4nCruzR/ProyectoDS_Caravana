package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class JuegoResumenDTO {
    private Long id;
    private String descripcion;
    private int tiempoTranscurridoDeJuego;
    private int tiempoLimiteDeJuego;
    private double nivelMinimoGananciasJuego;
    
    public JuegoResumenDTO() {
    }

    public JuegoResumenDTO(Long id, String descripcion, int tiempoTranscurridoDeJuego, int tiempoLimiteDeJuego,
            double nivelMinimoGananciasJuego) {
        this.id = id;
        this.descripcion = descripcion;
        this.tiempoTranscurridoDeJuego = tiempoTranscurridoDeJuego;
        this.tiempoLimiteDeJuego = tiempoLimiteDeJuego;
        this.nivelMinimoGananciasJuego = nivelMinimoGananciasJuego;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
