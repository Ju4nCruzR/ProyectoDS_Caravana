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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.JugadorService;

@Controller
@RequestMapping("/jugador")
public class JugadorController {
    
    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private CaravanaRepository caravanaRepository;

    // Caso 1: Formulario para crear jugador
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioCrear() {
        return new ModelAndView("jugadorTemplates/jugador-crear")
                .addObject("jugador", new Jugador())
                .addObject("caravanas", caravanaRepository.findAll());
    }

    // Caso 1 (POST): Crear jugador
    @PostMapping("/crear")
    public String crearJugador(@ModelAttribute Jugador jugador) {
        jugadorService.crearJugador(jugador);
        return "redirect:/jugador/list";
    }

    // Caso 2: Ver jugador
    @GetMapping("/{id}")
    public ModelAndView verJugador(@PathVariable Long id) {
        Jugador jugador = jugadorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        return new ModelAndView("jugadorTemplates/jugador-detalle")
        .addObject("jugador", jugador);
    }

    // Caso 3: Listar jugadores
    @GetMapping("/list")
    public ModelAndView listarJugadores() {
        List<Jugador> jugadores = jugadorService.listarTodos();
        return new ModelAndView("jugadorTemplates/jugador-list")
        .addObject("jugadores", jugadores);
    }

    // Caso 4: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Jugador jugador = jugadorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        return new ModelAndView("jugadorTemplates/jugador-editar")
                .addObject("jugador", jugador)
                .addObject("caravanas", caravanaRepository.findAll());
    }

    // Caso 4 (POST): Editar jugador
    @PostMapping("/{id}/editar")
    public String editarJugador(@PathVariable Long id, @ModelAttribute Jugador jugador) {
        jugadorService.actualizarJugador(id, jugador);
        return "redirect:/jugador/" + id;
    }

    // Caso 5: Eliminar jugador
    @PostMapping("/{id}/eliminar")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorService.eliminarJugador(id);
        return "redirect:/jugador/list";
    }
}
