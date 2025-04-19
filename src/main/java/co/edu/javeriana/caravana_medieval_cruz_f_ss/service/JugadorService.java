package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {
    
    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private CaravanaRepository caravanaRepository;

    // Caso 1: Crear jugador
    public Jugador crearJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    // Caso 2: Ver jugador por ID
    public Optional<Jugador> buscarPorId(Long id) {
        return jugadorRepository.findById(id);
    }

    // Caso 3: Listar todos
    public List<Jugador> listarTodos() {
        return jugadorRepository.findAll();
    }

    // Caso 4: Editar jugador
    public Jugador actualizarJugador(Long id, Jugador datos) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        jugador.setNombreJugador(datos.getNombreJugador());
        jugador.setRolJugador(datos.getRolJugador());

        if (datos.getCaravana() != null) {
            Caravana caravana = caravanaRepository.findById(datos.getCaravana().getId())
                    .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
            jugador.setCaravana(caravana);
        }

        return jugadorRepository.save(jugador);
    }

    // Caso 5: Eliminar jugador
    public void eliminarJugador(Long id) {
        jugadorRepository.deleteById(id);
    }

    // Caso 6: Listar jugadores por caravana
    public List<Jugador> listarPorCaravana(Caravana caravana) {
        return jugadorRepository.findAll().stream()
                .filter(j -> j.getCaravana() != null && j.getCaravana().equals(caravana))
                .toList();
    }
}
