package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Juego;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.JuegoService;

@Controller
@RequestMapping("/juego")
public class JuegoController {
    
    @Autowired
    private JuegoService juegoService;

    // Caso 1: Mostrar formulario de creación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioCrear() {
        return new ModelAndView("juegoTemplates/juego-crear")
        .addObject("juego", new Juego());
    }

    // Caso 1 (POST): Crear juego
    @PostMapping("/crear")
    public String crearJuego(@ModelAttribute Juego juego) {
        juegoService.crearJuego(juego);
        return "redirect:/juego/list";
    }

    // Caso 2: Ver detalle de un juego
    @GetMapping("/{id}")
    public ModelAndView verJuego(@PathVariable Long id) {
        Juego juego = juegoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        return new ModelAndView("juegoTemplates/juego-detalle")
        .addObject("juego", juego);
    }

    // Caso 3: Listar juegos
    @GetMapping("/list")
    public ModelAndView listarJuegos() {
        List<Juego> juegos = juegoService.listarTodos();
        return new ModelAndView("juegoTemplates/juego-list")
        .addObject("juegos", juegos);
    }

    // Caso 4: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Juego juego = juegoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        return new ModelAndView("juegoTemplates/juego-editar")
        .addObject("juego", juego);
    }

    // Caso 4 (POST): Editar juego
    @PostMapping("/{id}/editar")
    public String editarJuego(@PathVariable Long id, @ModelAttribute Juego nuevosDatos) {
        juegoService.actualizarJuego(id, nuevosDatos);
        return "redirect:/juego/" + id;
    }

    // Caso 5: Eliminar juego
    @PostMapping("/{id}/eliminar")
    public String eliminarJuego(@PathVariable Long id) {
        juegoService.eliminarJuego(id);
        return "redirect:/juego/list";
    }

    // Caso 6: Reiniciar tiempo
    @PostMapping("/{id}/reiniciar-tiempo")
    public String reiniciarTiempo(@PathVariable Long id) {
        juegoService.reiniciarTiempo(id);
        return "redirect:/juego/" + id;
    }

    // Caso 7: Avanzar tiempo
    @PostMapping("/{id}/avanzar-tiempo")
    public String avanzarTiempo(@PathVariable Long id, @RequestParam int minutos) {
        juegoService.avanzarTiempo(id, minutos);
        return "redirect:/juego/" + id;
    }

    // Caso 8: Ver caravanas y jugadores
    @GetMapping("/{id}/caravanas")
    public ModelAndView verCaravanas(@PathVariable Long id) {
        List<Caravana> caravanas = juegoService.obtenerCaravanas(id);
        return new ModelAndView("juegoTemplates/juego-caravanas")
        .addObject("caravanas", caravanas);
    }

    @GetMapping("/{id}/jugadores")
    public ModelAndView verJugadores(@PathVariable Long id) {
        List<Jugador> jugadores = juegoService.obtenerJugadores(id);
        return new ModelAndView("juegoTemplates/juego-jugadores")
        .addObject("jugadores", jugadores);
    }
}
