package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JugadorResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.JugadorMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Juego;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador.Rol;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.JuegoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.JugadorRepository;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    // Crear jugador
    public JugadorDTO crearJugador(JugadorDTO dto) {
        Jugador jugador = fromDTO(dto);
        jugador = jugadorRepository.save(jugador);
        return JugadorMapper.toDTO(jugador);
    }

    // Buscar jugador por ID
    public Optional<JugadorDTO> buscarPorId(Long id) {
        return jugadorRepository.findById(id)
                .map(JugadorMapper::toDTO);
    }

    // Listar todos los jugadores
    public List<JugadorDTO> listarTodos() {
        return jugadorRepository.findAll().stream()
                .map(JugadorMapper::toDTO)
                .toList();
    }

    // Actualizar jugador
    public JugadorDTO actualizarJugador(Long id, JugadorDTO datos) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        jugador.setNombreJugador(datos.getNombreJugador());
        jugador.setRolJugador(Rol.valueOf(datos.getRolJugador()));

        if (datos.getCaravanaId() != null) {
            Caravana caravana = caravanaRepository.findById(datos.getCaravanaId())
                    .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
            jugador.setCaravana(caravana);
        }

        jugador = jugadorRepository.save(jugador);
        return JugadorMapper.toDTO(jugador);
    }

    // Eliminar jugador
    public void eliminarJugador(Long id) {
    Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

    // Buscar juegos que contienen a este jugador
    List<Juego> juegos = juegoRepository.findByJugadorId(id);

    for (Juego juego : juegos) {
        juego.getJugadores().removeIf(j -> j.getId() == id);
        juegoRepository.save(juego);
    }

    // Ahora sí se puede eliminar el jugador
    jugadorRepository.deleteById(id);
}

    // Listar jugadores por caravana
    public List<JugadorResumenDTO> listarPorCaravana(Long caravanaId) {
        return jugadorRepository.findAll().stream()
                .filter(j -> j.getCaravana() != null && j.getCaravana().getId() == caravanaId)
                .map(JugadorMapper::toResumen)
                .toList();
    }

    // Conversión manual DTO → Entidad
    private Jugador fromDTO(JugadorDTO dto) {
        Caravana caravana = null;
        if (dto.getCaravanaId() != null) {
            caravana = caravanaRepository.findById(dto.getCaravanaId())
                    .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        }
        return new Jugador(caravana, dto.getNombreJugador(), Rol.valueOf(dto.getRolJugador()));
    }
}
