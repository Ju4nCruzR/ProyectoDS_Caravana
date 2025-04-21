package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.JugadorService;

@Controller
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private CaravanaRepository caravanaRepository;

    // Formulario para crear jugador
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioCrear() {
        return new ModelAndView("jugadorTemplates/jugador-crear")
                .addObject("jugador", new JugadorDTO())
                .addObject("caravanas", caravanaRepository.findAll());
    }

    // Crear jugador
    @PostMapping("/crear")
    public String crearJugador(@ModelAttribute JugadorDTO jugadorDTO) {
        jugadorService.crearJugador(jugadorDTO);
        return "redirect:/jugador/list";
    }

    // Ver jugador
    @GetMapping("/{id}")
    public ModelAndView verJugador(@PathVariable Long id) {
        JugadorDTO jugador = jugadorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        return new ModelAndView("jugadorTemplates/jugador-detalle")
                .addObject("jugador", jugador);
    }

    // Listar jugadores
    @GetMapping("/list")
    public ModelAndView listarJugadores() {
        List<JugadorDTO> jugadores = jugadorService.listarTodos();
        return new ModelAndView("jugadorTemplates/jugador-list")
                .addObject("jugadores", jugadores);
    }

    // Formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        JugadorDTO jugador = jugadorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        return new ModelAndView("jugadorTemplates/jugador-editar")
                .addObject("jugador", jugador)
                .addObject("caravanas", caravanaRepository.findAll());
    }

    // Editar jugador
    @PostMapping("/{id}/editar")
    public String editarJugador(@PathVariable Long id, @ModelAttribute JugadorDTO jugadorDTO) {
        jugadorService.actualizarJugador(id, jugadorDTO);
        return "redirect:/jugador/" + id;
    }

    // Mostrar vista de confirmación para eliminar jugador
    @GetMapping("/{id}/eliminar")
    public ModelAndView confirmarEliminarJugador(@PathVariable Long id) {
        JugadorDTO jugador = jugadorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        return new ModelAndView("jugadorTemplates/jugador-eliminar")
                .addObject("jugador", jugador);
    }

    // Eliminar jugador
    @PostMapping("/{id}/eliminar")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorService.eliminarJugador(id);
        return "redirect:/jugador/list";
    }
}
