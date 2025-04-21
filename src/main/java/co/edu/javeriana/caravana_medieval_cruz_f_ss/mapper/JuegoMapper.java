package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoFormularioDTO;
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
        dto.setTiempoTranscurridoDeJuego(juego.getTiempoTranscurridoDeJuego());
        dto.setTiempoLimiteDeJuego(juego.getTiempoLimiteDeJuego());
        dto.setNivelMinimoGananciasJuego(juego.getNivelMinimoGananciasJuego());

        String descripcion = "Juego #" + juego.getId() +
                " - Tiempo transcurrido: " + juego.getTiempoTranscurridoDeJuego() + " min" +
                ", Tiempo límite: " + juego.getTiempoLimiteDeJuego() + " min" +
                ", Nivel mínimo: " + juego.getNivelMinimoGananciasJuego();
        dto.setDescripcion(descripcion);
        return dto;
    }

    public static JuegoDetalleDTO toDetalleDTO(Juego juego) {
        JuegoDetalleDTO dto = new JuegoDetalleDTO();
        dto.setId(juego.getId());
        dto.setTiempoLimiteDeJuego(juego.getTiempoLimiteDeJuego());
        dto.setTiempoTranscurridoDeJuego(juego.getTiempoTranscurridoDeJuego());
        dto.setNivelMinimoGananciasJuego(juego.getNivelMinimoGananciasJuego());

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

    public static Juego fromFormulario(JuegoFormularioDTO dto) {
        Juego juego = new Juego();
    
        // Verifica de forma segura si el ID es distinto de null
        Long id = dto.getId();
        if (id != null && !id.equals(0L)) {
            juego.setId(id);
        }
    
        juego.setNivelMinimoGananciasJuego(dto.getNivelMinimoGananciasJuego());
        juego.setTiempoLimiteDeJuego(dto.getTiempoLimiteDeJuego());
    
        // Evita el warning de unboxing con tiempoTranscurrido
        Integer tiempoTranscurrido = dto.getTiempoTranscurridoDeJuego();
        juego.setTiempoTranscurridoDeJuego(tiempoTranscurrido != null ? tiempoTranscurrido : 0);
    
        return juego;
    }    

    public static JuegoFormularioDTO toFormularioDTO(Juego juego) {
        JuegoFormularioDTO dto = new JuegoFormularioDTO();
        dto.setId(juego.getId());
        dto.setNivelMinimoGananciasJuego(juego.getNivelMinimoGananciasJuego());
        dto.setTiempoLimiteDeJuego(juego.getTiempoLimiteDeJuego());
        dto.setTiempoTranscurridoDeJuego(juego.getTiempoTranscurridoDeJuego());
        return dto;

    }
}
