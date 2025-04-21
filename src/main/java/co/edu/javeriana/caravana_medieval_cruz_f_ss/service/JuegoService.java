package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.JuegoMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Juego;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.JuegoRepository;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    // Caso 1: Crear nuevo juego
    public JuegoDTO crearJuego(JuegoFormularioDTO dto) {
        Juego juego = JuegoMapper.fromFormulario(dto);
        return JuegoMapper.toDTO(juegoRepository.save(juego));
    }

    // Caso 2: Ver juego por ID
    public Optional<JuegoDetalleDTO> buscarPorId(Long id) {
        return juegoRepository.findById(id)
                .map(JuegoMapper::toDetalleDTO);
    }

    // Caso 3: Listar todos los juegos
    public List<JuegoResumenDTO> listarTodos() {
        return juegoRepository.findAll().stream()
                .map(JuegoMapper::toResumenDTO)
                .collect(Collectors.toList());
    }

    // Caso 4: Editar configuración
    public JuegoDTO actualizarJuego(Long id, JuegoFormularioDTO nuevosDatos) {
        Juego juego = juegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        juego.setNivelMinimoGananciasJuego(nuevosDatos.getNivelMinimoGananciasJuego());
        juego.setTiempoLimiteDeJuego(nuevosDatos.getTiempoLimiteDeJuego());
        juego.setTiempoTranscurridoDeJuego(nuevosDatos.getTiempoTranscurridoDeJuego());

        return JuegoMapper.toDTO(juegoRepository.save(juego));
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
    
    public JuegoDetalleDTO reiniciarTiempoYRetornar(Long id) {
        reiniciarTiempo(id); // método que hace la lógica real
        return buscarPorId(id).orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }

    // Caso 7: Avanzar tiempo
    public void avanzarTiempo(Long id, int minutos) {
        Juego juego = juegoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    
        int tiempoActual = juego.getTiempoTranscurridoDeJuego();
        juego.setTiempoTranscurridoDeJuego(tiempoActual + minutos);
    
        juegoRepository.save(juego);
    }    

    public JuegoDetalleDTO avanzarTiempoYRetornar(Long id, int minutos) {
        avanzarTiempo(id, minutos); // lógica real
        return buscarPorId(id).orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }
      
    // Caso 8: Obtener caravanas y jugadores en el juego
    public List<Caravana> obtenerCaravanas(Long juegoId) {
        return juegoRepository.findById(juegoId)
                .map(Juego::getCaravanas)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }

    public List<Jugador> obtenerJugadores(Long juegoId) {
        return juegoRepository.findById(juegoId)
                .map(Juego::getJugadores)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }

    public Optional<JuegoFormularioDTO> obtenerFormulario(Long id) {
        return juegoRepository.findById(id)
                .map(JuegoMapper::toFormularioDTO);
    }

}
