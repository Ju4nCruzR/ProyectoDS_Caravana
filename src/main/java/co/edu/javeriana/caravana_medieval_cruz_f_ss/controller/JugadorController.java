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
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.JugadorService;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    // Crear jugador
    @PostMapping
    public void crearJugador(@RequestBody JugadorDTO jugadorDTO) {
        jugadorService.crearJugador(jugadorDTO);
    }

    // Ver jugador por ID
    @GetMapping("/{id}")
    public JugadorDTO verJugador(@PathVariable Long id) {
        return jugadorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    // Listar todos los jugadores
    @GetMapping("/list")
    public List<JugadorDTO> listarJugadores() {
        return jugadorService.listarTodos();
    }

    // Editar jugador
    @PutMapping("/{id}")
    public void editarJugador(@PathVariable Long id, @RequestBody JugadorDTO jugadorDTO) {
        jugadorService.actualizarJugador(id, jugadorDTO);
    }

    // Eliminar jugador
    @DeleteMapping("/{id}")
    public void eliminarJugador(@PathVariable Long id) {
        jugadorService.eliminarJugador(id);
    }
}
