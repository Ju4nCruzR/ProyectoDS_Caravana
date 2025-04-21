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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JuegoResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
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
        ModelAndView modelAndView = new ModelAndView("juegoTemplates/juego-crear");
        modelAndView.addObject("juego", new JuegoFormularioDTO()); // ← Aquí se incluye el objeto esperado por el
                                                                   // formulario
        return modelAndView;
    }

    // Caso 1 (POST): Crear juego
    @PostMapping("/crear")
    public String crearJuego(@ModelAttribute("juego") JuegoFormularioDTO juegoDTO) {
        juegoService.crearJuego(juegoDTO);
        return "redirect:/juego/list";
    }

    // Caso 2: Ver detalle de un juego
    @GetMapping("/{id}")
    public ModelAndView verJuego(@PathVariable Long id) {
        JuegoDetalleDTO dto = juegoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        return new ModelAndView("juegoTemplates/juego-detalle")
                .addObject("juego", dto);
    }

    // Caso 3: Listar juegos
    @GetMapping("/list")
    public ModelAndView listarJuegos() {
        List<JuegoResumenDTO> juegos = juegoService.listarTodos();
        return new ModelAndView("juegoTemplates/juego-list")
                .addObject("juegos", juegos);
    }

    // Caso 4: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        JuegoFormularioDTO dto = juegoService.obtenerFormulario(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        return new ModelAndView("juegoTemplates/juego-editar")
                .addObject("juego", dto); // <- debe estar como "juego"
    }

    // Caso 4 (POST): Editar juego
    @PostMapping("/{id}/editar")
    public String editarJuego(@PathVariable Long id, @ModelAttribute JuegoFormularioDTO dto) {
        juegoService.actualizarJuego(id, dto);
        return "redirect:/juego/" + id;
    }

    // Caso 5: Eliminar juego
    @GetMapping("/{id}/eliminar")
    public ModelAndView confirmarEliminacion(@PathVariable Long id) {
        JuegoDetalleDTO juego = juegoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        return new ModelAndView("juegoTemplates/juego-eliminar")
                .addObject("juego", juego);
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarJuego(@PathVariable Long id) {
        juegoService.eliminarJuego(id);
        return "redirect:/juego/list";
    }

    // Caso 6: Reiniciar tiempo
    @GetMapping("/{id}/reiniciar-tiempo")
    public ModelAndView mostrarConfirmacionReinicio(@PathVariable Long id) {
        JuegoDetalleDTO juego = juegoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        return new ModelAndView("juegoTemplates/juego-reiniciar-tiempo")
                .addObject("juego", juego);
    }

    @PostMapping("/{id}/reiniciar-tiempo")
    public ModelAndView reiniciarTiempo(@PathVariable Long id) {
        JuegoDetalleDTO juego = juegoService.reiniciarTiempoYRetornar(id);
        return new ModelAndView("juegoTemplates/juego-detalle").addObject("juego", juego);
    }

    // Caso 7: Avanzar tiempo
    @GetMapping("/{id}/avanzar-tiempo")
    public ModelAndView mostrarFormularioAvanzarTiempo(@PathVariable Long id) {
        JuegoDetalleDTO juego = juegoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        return new ModelAndView("juegoTemplates/juego-avanzar-tiempo")
                .addObject("juego", juego);
    }

    @PostMapping("/{id}/avanzar-tiempo")
    public ModelAndView avanzarTiempo(@PathVariable Long id, @RequestParam int minutos) {
        JuegoDetalleDTO juego = juegoService.avanzarTiempoYRetornar(id, minutos);
        return new ModelAndView("juegoTemplates/juego-detalle").addObject("juego", juego);
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
