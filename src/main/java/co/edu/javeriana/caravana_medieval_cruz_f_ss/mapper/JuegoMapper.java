package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JugadorResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Juego;

public class JuegoMapper {

    public static JuegoDTO toDTO(Juego juego) {
        JuegoDTO dto = new JuegoDTO();
        dto.setId(juego.getId());
        dto.setTiempoLimiteDeJuego(juego.getTiempoLimiteDeJuego());
        dto.setTiempoTranscurridoDeJuego(juego.getTiempoTranscurridoDeJuego());
        dto.setNivelMinimoGananciasJuego(juego.getNivelMinimoGananciasJuego());
        return dto;
    }

    public static JuegoResumenDTO toResumenDTO(Juego juego) {
        JuegoResumenDTO dto = new JuegoResumenDTO();
        dto.setId(juego.getId());
        dto.setDescripcion("Juego #" + juego.getId() +
                " - Tiempo: " + juego.getTiempoTranscurridoDeJuego() + "/" + juego.getTiempoLimiteDeJuego());
        return dto;
    }

    public static JuegoDetalleDTO toDetalleDTO(Juego juego) {
        JuegoDetalleDTO dto = new JuegoDetalleDTO();
        dto.setJuego(toDTO(juego));

        List<CaravanaResumenDTO> caravanas = juego.getCaravanas().stream()
            .map(CaravanaMapper::toResumen)
            .collect(Collectors.toList());
        dto.setCaravanas(caravanas);

        List<JugadorResumenDTO> jugadores = juego.getJugadores().stream()
            .map(JugadorMapper::toResumen)
            .collect(Collectors.toList());
        dto.setJugadores(jugadores);

        return dto;
    }
}
