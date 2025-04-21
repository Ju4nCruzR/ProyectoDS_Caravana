package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.JuegoService;

@RestController
@RequestMapping("/juego")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    // Crear juego
    @PostMapping
    public void crearJuego(@RequestBody JuegoFormularioDTO juegoDTO) {
        juegoService.crearJuego(juegoDTO);
    }

    // Ver detalle
    @GetMapping("/{id}")
    public JuegoDetalleDTO verJuego(@PathVariable Long id) {
        return juegoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }

    // Listar todos los juegos
    @GetMapping("/list")
    public List<JuegoResumenDTO> listarJuegos() {
        return juegoService.listarTodos();
    }

    // Obtener formulario de edición (opcional, si lo necesitas como recurso REST)
    @GetMapping("/{id}/formulario")
    public JuegoFormularioDTO obtenerFormulario(@PathVariable Long id) {
        return juegoService.obtenerFormulario(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }

    // Editar juego
    @PutMapping("/{id}")
    public void editarJuego(@PathVariable Long id, @RequestBody JuegoFormularioDTO dto) {
        juegoService.actualizarJuego(id, dto);
    }

    // Eliminar juego
    @DeleteMapping("/{id}")
    public void eliminarJuego(@PathVariable Long id) {
        juegoService.eliminarJuego(id);
    }

    // Reiniciar tiempo del juego
    @PostMapping("/{id}/reiniciar-tiempo")
    public JuegoDetalleDTO reiniciarTiempo(@PathVariable Long id) {
        return juegoService.reiniciarTiempoYRetornar(id);
    }

    // Avanzar tiempo del juego
    @PostMapping("/{id}/avanzar-tiempo")
    public JuegoDetalleDTO avanzarTiempo(@PathVariable Long id, @RequestParam int minutos) {
        return juegoService.avanzarTiempoYRetornar(id, minutos);
    }

    // Ver caravanas del juego
    @GetMapping("/{id}/caravanas")
    public List<Caravana> verCaravanas(@PathVariable Long id) {
        return juegoService.obtenerCaravanas(id);
    }

    // Ver jugadores del juego
    @GetMapping("/{id}/jugadores")
    public List<Jugador> verJugadores(@PathVariable Long id) {
        return juegoService.obtenerJugadores(id);
    }
}
