package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JugadorResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;

public class JugadorMapper {
    
    public static JugadorDTO toDTO(Jugador jugador) {
        JugadorDTO dto = new JugadorDTO();
        dto.setId(jugador.getId());
        dto.setNombreJugador(jugador.getNombreJugador());
        dto.setRolJugador(jugador.getRolJugador().name());

        if (jugador.getCaravana() != null) {
            dto.setCaravanaId(jugador.getCaravana().getId());
            dto.setNombreCaravana(jugador.getCaravana().getNombreCaravana());
        }

        return dto;
    }

    public static JugadorResumenDTO toResumen(Jugador jugador) {
        JugadorResumenDTO dto = new JugadorResumenDTO();
        dto.setId(jugador.getId());
        dto.setNombreJugador(jugador.getNombreJugador());
        dto.setRolJugador(jugador.getRolJugador().name());
        return dto;
    }
}
