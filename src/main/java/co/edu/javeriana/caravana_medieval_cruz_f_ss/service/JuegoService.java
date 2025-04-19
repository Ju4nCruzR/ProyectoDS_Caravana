package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Juego;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.JuegoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    // Caso 1: Crear nuevo juego
    public Juego crearJuego(Juego juego) {
        return juegoRepository.save(juego);
    }

    // Caso 2: Ver juego por ID
    public Optional<Juego> buscarPorId(Long id) {
        return juegoRepository.findById(id);
    }

    // Caso 3: Listar todos los juegos
    public List<Juego> listarTodos() {
        return juegoRepository.findAll();
    }

    // Caso 4: Editar configuración
    public Juego actualizarJuego(Long id, Juego nuevosDatos) {
        Juego juego = juegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        juego.setNivelMinimoGananciasJuego(nuevosDatos.getNivelMinimoGananciasJuego());
        juego.setTiempoLimiteDeJuego(nuevosDatos.getTiempoLimiteDeJuego());
        return juegoRepository.save(juego);
    }

    // Caso 5: Eliminar juego
    public void eliminarJuego(Long id) {
        juegoRepository.deleteById(id);
    }

    // Caso 6: Reiniciar tiempo
    public void reiniciarTiempo(Long id) {
        Juego juego = juegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        juego.setTiempoTranscurridoDeJuego(0);
        juegoRepository.save(juego);
    }

    // Caso 7: Avanzar tiempo
    public void avanzarTiempo(Long id, int minutos) {
        Juego juego = juegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        juego.setTiempoTranscurridoDeJuego(
                juego.getTiempoTranscurridoDeJuego() + minutos
        );
        juegoRepository.save(juego);
    }

    // Caso 8: Obtener caravanas y jugadores en el juego
    public List<Caravana> obtenerCaravanas(Long juegoId) {
        return buscarPorId(juegoId).map(Juego::getCaravanas)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }

    public List<Jugador> obtenerJugadores(Long juegoId) {
        return buscarPorId(juegoId).map(Juego::getJugadores)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }
}
