package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CaravanaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/caravana")
public class CaravanaController {
    @Autowired
    private CaravanaService caravanaService;

    // Caso 1: crear caravana
    @GetMapping("/nueva")
    public ModelAndView mostrarFormularioCrearCaravana() {
        return new ModelAndView("caravana-form").addObject("caravana", new Caravana());
    }

    // Caso 2: consultar caravana por ID
    @GetMapping("/{id}")
    public ModelAndView verCaravana(@PathVariable Long id) {
        Caravana caravana = caravanaService.buscarCaravanaPorId(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        return new ModelAndView("caravana-detalle").addObject("caravana", caravana);
    }

    // Caso 3: listar todas las caravanas
    @GetMapping("/list")
    public ModelAndView listarCaravanas() {
        List<Caravana> caravanas = caravanaService.listarCaravanas();
        ModelAndView modelAndView = new ModelAndView("caravana-list");
        modelAndView.addObject("listaCaravanas", caravanas);
        return modelAndView;
    }

    // Caso 4: mover caravana a otra ciudad
    @PostMapping("/{id}/mover")
    public String moverCaravana(@PathVariable Long id, @RequestParam Long ciudadId) {
        caravanaService.moverCaravana(id, ciudadId);
        return "redirect:/caravana/" + id;
    }

    // Caso 5: comprar producto
    @PostMapping("/{id}/comprar")
    public String comprarProducto(@PathVariable Long id,
            @RequestParam Long productoId,
            @RequestParam int cantidad) {
        caravanaService.comprarProducto(id, productoId, cantidad);
        return "redirect:/caravana/" + id;
    }

    // Caso 6: vender producto
    @PostMapping("/{id}/vender")
    public String venderProducto(@PathVariable Long id,
            @RequestParam Long productoId,
            @RequestParam int cantidad) {
        caravanaService.venderProducto(id, productoId, cantidad);
        return "redirect:/caravana/" + id;
    }

    // Caso 7: aplicar servicio
    @PostMapping("/{id}/servicio")
    public String aplicarServicio(@PathVariable Long id,
            @RequestParam Long servicioId) {
        caravanaService.aplicarServicio(id, servicioId);
        return "redirect:/caravana/" + id;
    }

    // Caso 8: obtener productos (retorno parcial como JSON)
    @GetMapping("/{id}/productos")
    @ResponseBody
    public List<CaravanaProducto> obtenerProductos(@PathVariable Long id) {
        return caravanaService.obtenerProductos(id);
    }

    // Caso 9: eliminar caravana
    @PostMapping("/{id}/eliminar")
    public String eliminarCaravana(@PathVariable Long id) {
        caravanaService.eliminarCaravana(id);
        return "redirect:/caravana/list";
    }

    // Caso 10: agregar jugador
    @PostMapping("/{id}/jugadores")
    public String agregarJugador(@PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam Jugador.Rol rol) {
        caravanaService.agregarJugador(id, nombre, rol);
        return "redirect:/caravana/" + id;
    }
}
