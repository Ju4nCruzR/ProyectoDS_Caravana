package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class JuegoDetalleDTO {
    private Long id;
    private JuegoDTO juego;
    private List<CaravanaResumenDTO> caravanas;
    private List<JugadorResumenDTO> jugadores;
    
    public JuegoDetalleDTO() {
    }

    public JuegoDetalleDTO(Long id, JuegoDTO juego, List<CaravanaResumenDTO> caravanas,
            List<JugadorResumenDTO> jugadores) {
        this.id = id;
        this.juego = juego;
        this.caravanas = caravanas;
        this.jugadores = jugadores;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public JuegoDTO getJuego() {
        return juego;
    }

    public void setJuego(JuegoDTO juego) {
        this.juego = juego;
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
